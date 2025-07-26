package com.movie.tmdb.testutil

import androidx.paging.PagingData
import com.movie.tmdb.core.network.model.PaginatedResponse
import com.movie.tmdb.feat.movie_list.data.datasource.remote.model.RemoteMovie
import com.movie.tmdb.feat.movie_list.domain.model.Movie

/**
 * Factory for creating common test data structures.
 * Provides consistent test data for responses and collections.
 */
object TestDataFactory {

	fun createPaginatedResponse(
		results: List<RemoteMovie> = TestMovieFactory.createRemoteMovieList(),
		page: Int = 1,
		totalPages: Int = 5,
		totalResults: Int = results.size
	): PaginatedResponse<RemoteMovie> {
		return PaginatedResponse(
			results = results,
			page = page,
			totalPages = totalPages,
			totalResults = totalResults
		)
	}

	fun createMoviePagingData(
		movies: List<Movie> = TestMovieFactory.createMovieList()
	): PagingData<Movie> {
		return PagingData.from(movies)
	}

	fun createEmptyPaginatedResponse(
		page: Int = 1,
		totalPages: Int = 0
	): PaginatedResponse<RemoteMovie> {
		return PaginatedResponse(
			results = emptyList(),
			page = page,
			totalPages = totalPages,
			totalResults = 0
		)
	}
} 