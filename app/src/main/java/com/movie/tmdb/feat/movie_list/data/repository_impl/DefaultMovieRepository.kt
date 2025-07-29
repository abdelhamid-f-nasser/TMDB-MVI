package com.movie.tmdb.feat.movie_list.data.repository_impl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.movie.tmdb.core.constants.ApiConstants
import com.movie.tmdb.feat.movie_list.data.datasource.local.MovieLocalDataSource
import com.movie.tmdb.feat.movie_list.data.datasource.local.model.mapper.toDomain
import com.movie.tmdb.feat.movie_list.data.datasource.remote.MovieRemoteDataSource
import com.movie.tmdb.feat.movie_list.data.datasource.remote.paging.MovieRemoteMediator
import com.movie.tmdb.feat.movie_list.data.datasource.remote.paging.SearchPagingSource

import com.movie.tmdb.feat.movie_list.domain.model.Movie
import com.movie.tmdb.feat.movie_list.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultMovieRepository @Inject constructor(
	private val localDataSource: MovieLocalDataSource,
	private val remoteDataSource: MovieRemoteDataSource,
) : MovieRepository {
	@OptIn(ExperimentalPagingApi::class)
	override fun getPopularMovies(): Flow<PagingData<Movie>> {
		return Pager(
			config = PagingConfig(
				pageSize = ApiConstants.Pagination.DEFAULT_PAGE_SIZE,
				initialLoadSize = ApiConstants.Pagination.INITIAL_LOAD_SIZE,
				enablePlaceholders = false,
				prefetchDistance = 2,
			),
			remoteMediator = MovieRemoteMediator(localDataSource, remoteDataSource),
		){
			localDataSource.getPopularMovies()
		}.flow.map {pagingData ->
			pagingData.map {
				it.toDomain()
			}
		}
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
