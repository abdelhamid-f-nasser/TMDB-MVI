package com.movie.tmdb.feat.movie_list.data.datasource.remote.paging

import androidx.paging.PagingSource
import com.movie.tmdb.feat.movie_list.data.datasource.remote.MovieRemoteDataSource
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import com.movie.tmdb.testutil.TestDataFactory
import com.movie.tmdb.testutil.TestMovieFactory
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MoviePagingSourceTest {

	private lateinit var sut: MoviePagingSource
	private lateinit var mockRemoteDataSource: MovieRemoteDataSource

	@BeforeEach
	fun setUp() {
		mockRemoteDataSource = mockk<MovieRemoteDataSource>()
		sut = MoviePagingSource(mockRemoteDataSource)
	}

	@Test
	fun `load should return Page with Movie domain models when data fetching succeeds`() = runTest {
		// Arrange
		val remoteMovies = listOf(
			TestMovieFactory.createRemoteMovie(id = 1, title = "Test Movie 1"),
			TestMovieFactory.createRemoteMovie(id = 2, title = "Test Movie 2")
		)
		val paginatedResponse = TestDataFactory.createPaginatedResponse(
			results = remoteMovies,
			page = 1,
			totalPages = 5,
			totalResults = 100
		)
		coEvery { mockRemoteDataSource.getPopularMovies(1) } returns paginatedResponse

		// Act
		val result = sut.load(
			PagingSource.LoadParams.Refresh(key = null, loadSize = 20, placeholdersEnabled = false)
		)

		// Assert
		assertTrue(result is PagingSource.LoadResult.Page)
		val pageResult = result as PagingSource.LoadResult.Page
		assertEquals(2, pageResult.data.size)
		assertTrue(pageResult.data.all { it is Movie })
		assertEquals("1", pageResult.data[0].id)
		assertEquals("Test Movie 1", pageResult.data[0].title)
	}

	@Test
	fun `load should return Error when remote data source throws exception`() = runTest {
		// Arrange
		val exception = RuntimeException("Network error")
		coEvery { mockRemoteDataSource.getPopularMovies(any()) } throws exception

		// Act
		val result = sut.load(
			PagingSource.LoadParams.Refresh(key = null, loadSize = 20, placeholdersEnabled = false)
		)

		// Assert
		assertTrue(result is PagingSource.LoadResult.Error)
		val errorResult = result as PagingSource.LoadResult.Error
		assertEquals(exception, errorResult.throwable)
	}

	@Test
	fun `load should fetch data with correct page number`() = runTest {
		// Arrange
		val pageNumber = 3
		val paginatedResponse = TestDataFactory.createEmptyPaginatedResponse(
			page = pageNumber,
			totalPages = 5
		)
		coEvery { mockRemoteDataSource.getPopularMovies(pageNumber) } returns paginatedResponse

		// Act
		val result = sut.load(
			PagingSource.LoadParams.Refresh(key = pageNumber, loadSize = 20, placeholdersEnabled = false)
		)

		// Assert
		assertTrue(result is PagingSource.LoadResult.Page)
	}
}
