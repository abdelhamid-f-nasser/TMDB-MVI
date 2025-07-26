package com.movie.tmdb.feat.movie_list.data.datasource.remote.paging

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
            val page = params.key ?: ApiConstants.Pagination.START_PAGE_NUMBER

            val response: PaginatedResponse<RemoteMovie> = remoteDataSource.getPopularMovies(
                page = page,
            )

            val movies = response.results.map { remoteMovie ->
                remoteMovie.toDomain()
            }

            val nextKey = if (page < response.totalPages) {
                page + 1
            } else {
                null
            }

            val prevKey = if (page > 1) {
                page - 1
            } else {
                null
            }

            LoadResult.Page(
                data = movies,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }


}
