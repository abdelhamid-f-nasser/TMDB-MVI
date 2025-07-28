package com.movie.tmdb.feat.movie_list.data.datasource.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.movie.tmdb.core.constants.ApiConstants
import com.movie.tmdb.core.network.model.PaginatedResponse
import com.movie.tmdb.feat.movie_list.data.datasource.remote.MovieRemoteDataSource
import com.movie.tmdb.feat.movie_list.data.datasource.remote.TmdbService
import com.movie.tmdb.feat.movie_list.data.datasource.remote.model.RemoteMovie
import com.movie.tmdb.feat.movie_list.data.datasource.remote.model.mapper.toDomain
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import javax.inject.Inject

/**
 * PagingSource for loading movies from TMDB API.
 *
 * Handles pagination by loading pages of movies and converting them to domain models.
 */
class MoviePagingSource @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = remoteDataSource.getPopularMovies(page)
            
            LoadResult.Page(
                data = response.results.map { it.toDomain() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
			Log.e("MoviePagingSource", "Error loading movies: ${exception.message}")
            LoadResult.Error(exception)
        }
    }


}
