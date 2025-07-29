package com.movie.tmdb.feat.movie_list.data.repository_impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.movie.tmdb.core.constants.ApiConstants
import com.movie.tmdb.feat.movie_list.data.datasource.remote.MovieRemoteDataSource
import com.movie.tmdb.feat.movie_list.data.datasource.remote.paging.MoviePagingSource
import com.movie.tmdb.feat.movie_list.data.datasource.remote.paging.SearchPagingSource
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import com.movie.tmdb.feat.movie_list.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultMovieRepository @Inject constructor(
	private val moviePagingSource: MoviePagingSource,
	private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {
	override fun getPopularMovies(): Flow<PagingData<Movie>> {
		return Pager(
			config = PagingConfig(
				pageSize = ApiConstants.Pagination.DEFAULT_PAGE_SIZE,
				initialLoadSize = ApiConstants.Pagination.INITIAL_LOAD_SIZE,
				enablePlaceholders = false,
			),
			pagingSourceFactory = { moviePagingSource }
		).flow
	}

	override fun searchMovies(query: String): Flow<PagingData<Movie>> {
		return Pager(
			config = PagingConfig(
				pageSize = ApiConstants.Pagination.DEFAULT_PAGE_SIZE,
				initialLoadSize = ApiConstants.Pagination.INITIAL_LOAD_SIZE,
				enablePlaceholders = false,
			),
			pagingSourceFactory = { SearchPagingSource(remoteDataSource, query) }
		).flow
	}


}
