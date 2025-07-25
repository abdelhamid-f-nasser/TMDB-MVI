package com.movie.tmdb.feat.movie_list.domain.repository

import com.movie.tmdb.feat.movie_list.domain.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Single source of truth for Movie related
 */
interface MovieRepository {
	fun getPopularMovies(): Flow<List<Movie>>
}
