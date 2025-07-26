package com.movie.tmdb.feat.movie_list.domain.usecase

import androidx.paging.PagingData
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import com.movie.tmdb.feat.movie_list.domain.repository.MovieRepository
import com.movie.tmdb.foundation.domain.usecase.OutputOnlyUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for fetching a paginated list of movies.
 */
class GetMoviesUseCase @Inject constructor(
	private val repository: MovieRepository
) : OutputOnlyUseCase<Flow<PagingData<Movie>>> {

	override suspend fun invoke(): Flow<PagingData<Movie>> = repository.getPopularMovies()
}
