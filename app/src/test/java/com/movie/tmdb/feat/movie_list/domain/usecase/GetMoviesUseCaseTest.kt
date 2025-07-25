package com.movie.tmdb.feat.movie_list.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import com.movie.tmdb.feat.movie_list.domain.repository.MovieRepository
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
	fun `GetMoviesUseCase should call repository and return a list of movies`() = runTest {
		// Arrange
		val expected =
			listOf(
				Movie(
					"1", "", overview = "",
					voteAverage = 0.0,
					posterPath = "",
					backdropPath = "",
				),
				Movie(
					"2", "",
					overview = "",
					voteAverage = 0.0,
					posterPath = "",
					backdropPath = "",
				),
			)

		every { repoMock.getPopularMovies() } returns flow { emit(expected) }


		// Act
		val actual = sut()

		// Assert
		verify(exactly = 1) { repoMock.getPopularMovies() }

		actual.test {
			assertThat(awaitItem()).isEqualTo(expected)
			awaitComplete()
		}
	}
}
