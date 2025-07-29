package com.movie.tmdb.feat.movie_list.data.datasource.remote

import com.movie.tmdb.core.network.model.PaginatedResponse
import com.movie.tmdb.feat.movie_list.data.datasource.remote.model.RemoteMovie

interface MovieRemoteDataSource {
    /**
     * Fetches a paginated list of movies from a remote source.
     * @param page The page number to fetch.
     * @return [PaginatedResponse] containing [RemoteMovie] items.
     */
	suspend fun getPopularMovies(page: Int): PaginatedResponse<RemoteMovie>
	suspend fun searchMovies(query: String, page: Int): PaginatedResponse<RemoteMovie>
}
