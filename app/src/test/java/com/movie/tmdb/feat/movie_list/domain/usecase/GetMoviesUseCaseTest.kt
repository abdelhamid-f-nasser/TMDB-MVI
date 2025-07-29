package com.movie.tmdb.feat.movie_list.domain.usecase

import androidx.paging.PagingData
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import com.movie.tmdb.feat.movie_list.domain.repository.MovieRepository
import com.movie.tmdb.testutil.TestDataFactory
import com.movie.tmdb.testutil.TestMovieFactory
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetMoviesUseCaseTest {
	lateinit var repoMock: MovieRepository
	lateinit var sut: GetMoviesUseCase

	@BeforeEach
	fun setup() {
		repoMock = mockk()
		sut = GetMoviesUseCase(repoMock)
	}

	@Test
	fun `GetMoviesUseCase should call repository and return PagingData flow`() = runTest {
		// Arrange
		val expectedMovies = listOf(
			TestMovieFactory.createMovie(
				id = "1",
				title = "Movie 1",
				overview = "Overview 1",
				voteAverage = 7.5
			),
			TestMovieFactory.createMovie(
				id = "2",
				title = "Movie 2",
				overview = "Overview 2",
				voteAverage = 8.0
			),
		)

		val expectedPagingData = TestDataFactory.createMoviePagingData(expectedMovies)
		every { repoMock.getPopularMovies() } returns flow { emit(expectedPagingData) }

		// Act
		val actual = sut()

		// Assert
		actual.test {
			val pagingData = awaitItem()
			assertThat(pagingData).isNotNull()
			awaitComplete()
		}

		verify(exactly = 1) { repoMock.getPopularMovies() }
	}

	@Test
	fun `GetMoviesUseCase should return the same flow from repository`() = runTest {
		// Arrange
		val repositoryFlow = flow { emit(PagingData.from(emptyList<Movie>())) }
		every { repoMock.getPopularMovies() } returns repositoryFlow

		// Act
		val result = sut()

		// Assert
		assertThat(result).isEqualTo(repositoryFlow)
		verify(exactly = 1) { repoMock.getPopularMovies() }
	}

	@Test
	fun `GetMoviesUseCase should delegate to repository without any transformation`() = runTest {
		// Arrange
		val testMovies = TestMovieFactory.createMovieList(count = 2)
		val testPagingData = TestDataFactory.createMoviePagingData(testMovies)
		every { repoMock.getPopularMovies() } returns flow { emit(testPagingData) }

		// Act
		val result = sut()

		// Assert - Verify the flow emits the expected PagingData
		result.test {
			val emittedPagingData = awaitItem()
			assertThat(emittedPagingData).isNotNull()
			awaitComplete()
		}

		verify(exactly = 1) { repoMock.getPopularMovies() }
	}
}
