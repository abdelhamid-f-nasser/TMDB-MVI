package com.movie.tmdb.feat.movie_list.data.datasource.remote.paging

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.movie.tmdb.core.constants.ApiConstants.Pagination.START_PAGE_NUMBER
import com.movie.tmdb.core.network.NoConnectivityException
import com.movie.tmdb.feat.movie_list.data.datasource.local.MovieLocalDataSource
import com.movie.tmdb.feat.movie_list.data.datasource.local.model.LocalMovie
import com.movie.tmdb.feat.movie_list.data.datasource.remote.MovieRemoteDataSource
import com.movie.tmdb.feat.movie_list.data.datasource.remote.model.mapper.toLocal
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator @Inject constructor(
	private val localDataSource: MovieLocalDataSource,
	private val remoteDataSource: MovieRemoteDataSource,
) : RemoteMediator<Int, LocalMovie>() {

	private var pageNumber = START_PAGE_NUMBER

	@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
	override suspend fun load(
		loadType: LoadType,
		state: PagingState<Int, LocalMovie>,
	): MediatorResult {
		return try {
			when (loadType) {
				LoadType.REFRESH -> {
					pageNumber = START_PAGE_NUMBER
				}

				LoadType.PREPEND -> {
					return MediatorResult.Success(
						endOfPaginationReached = true
					)
				}

				else -> {

				}
			}

			val response = remoteDataSource.getPopularMovies(pageNumber)
			pageNumber++

			val localMovieList = response.results.map { it.toLocal() }
			if (loadType == LoadType.REFRESH) {
				localDataSource.insertAndDeleteOldMovies(localMovieList)
			} else {
				localDataSource.insertAllMovies(localMovieList)
			}

			MediatorResult.Success(
				endOfPaginationReached = pageNumber > response.totalPages
			)
		} catch (e: NoConnectivityException) {
			val cachedDataExists = state.pages.isNotEmpty() && state.pages.first().data.isNotEmpty()

			if (cachedDataExists) {
				MediatorResult.Success(
					endOfPaginationReached = false
				)
			} else {
				MediatorResult.Error(e)
			}
		} catch (e: IOException) {
			val cachedDataExists = state.pages.isNotEmpty() && state.pages.first().data.isNotEmpty()

			if (cachedDataExists) {
				MediatorResult.Success(
					endOfPaginationReached = false
				)
			} else {
				MediatorResult.Error(e)
			}
		} catch (e: HttpException) {
			val cachedDataExists = state.pages.isNotEmpty() && state.pages.first().data.isNotEmpty()

			if (cachedDataExists) {
				MediatorResult.Success(
					endOfPaginationReached = false
				)
			} else {
				MediatorResult.Error(e)
			}
		}
	}
}
