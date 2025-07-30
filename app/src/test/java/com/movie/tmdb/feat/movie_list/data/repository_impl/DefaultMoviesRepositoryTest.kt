package com.movie.tmdb.feat.movie_list.data.repository_impl

import androidx.paging.PagingData
import com.google.common.truth.Truth.assertThat
import com.movie.tmdb.feat.movie_list.data.datasource.local.MovieRoomLocalDataSource
import com.movie.tmdb.feat.movie_list.data.datasource.remote.MovieRetrofitRemoteDataSource
import com.movie.tmdb.feat.movie_list.data.datasource.remote.paging.MoviePagingSource
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DefaultMoviesRepositoryTest {

	private lateinit var sut: DefaultMovieRepository
	private lateinit var mockMoviePagingSource: MoviePagingSource
	private lateinit var mockRemoteDataSource: MovieRetrofitRemoteDataSource
	private lateinit var mockLocalDataSource: MovieRoomLocalDataSource

	@BeforeEach
	fun setUp() {
		mockMoviePagingSource = mockk<MoviePagingSource>()
		mockRemoteDataSource = mockk<MovieRetrofitRemoteDataSource>()
		mockLocalDataSource = mockk<MovieRoomLocalDataSource>()

		sut = DefaultMovieRepository(mockLocalDataSource, mockRemoteDataSource)
	}

	@Test
	@DisplayName("Should return Flow of PagingData when getPopularMovies is called")
	fun `getPopularMovies returns Flow of PagingData`() = runTest {
		// Act
		val result = sut.getPopularMovies()

		// Assert
		assertThat(result is Flow<PagingData<Movie>>)
	}

	@Test
	@DisplayName("Should return Flow of PagingData when searchMovies is called")
	fun `searchMovies returns Flow of PagingData`() = runTest {
		// Arrange
		val query = "key"

		// Act
		val result = sut.searchMovies(query)

		// Assert
		assertThat(result is Flow<PagingData<Movie>>)
	}
}
