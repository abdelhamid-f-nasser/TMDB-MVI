package com.movie.tmdb.feat.movie_list.domain.usecase

import androidx.paging.PagingData
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import com.movie.tmdb.feat.movie_list.domain.repository.MovieRepository
import com.movie.tmdb.foundation.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for fetching a paginated list of movies.
 */
open class SearchMoviesUseCase @Inject constructor(
	private val repository: MovieRepository
) : UseCase<String, Flow<PagingData<Movie>>> {

	override fun invoke(input: String): Flow<PagingData<Movie>> = repository.searchMovies(input)
}
