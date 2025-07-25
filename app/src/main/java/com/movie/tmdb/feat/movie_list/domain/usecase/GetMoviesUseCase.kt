package com.movie.tmdb.feat.movie_list.domain.usecase

import com.movie.tmdb.feat.movie_list.domain.model.Movie
import com.movie.tmdb.feat.movie_list.domain.repository.MovieRepository
import com.movie.tmdb.foundation.domain.usecase.OutputOnlyUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for fetching a list of movies.
 */
class GetMoviesUseCase @Inject constructor(
	private val repository: MovieRepository
) : OutputOnlyUseCase<Flow<List<Movie>>> {

	override suspend fun invoke(): Flow<List<Movie>> = repository.getPopularMovies()
}
