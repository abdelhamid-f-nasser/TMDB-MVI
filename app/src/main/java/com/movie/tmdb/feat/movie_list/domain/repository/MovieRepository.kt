package com.movie.tmdb.feat.movie_list.domain.repository

import androidx.paging.PagingData
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Single source of truth for Movie related data.
 */
interface MovieRepository {
	// Domain layer should be framework agnostic,
	// exposing data as [PagingData] here is a clear violation to it.
	fun getPopularMovies(): Flow<PagingData<Movie>>
	fun searchMovies(query: String): Flow<PagingData<Movie>>
}
