package com.movie.tmdb.feat.movie_list.data.datasource.remote

import com.movie.tmdb.core.constants.ApiConstants
import com.movie.tmdb.core.network.model.PaginatedResponse
import com.movie.tmdb.feat.movie_list.data.datasource.remote.model.RemoteMovie
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {

	@GET(ApiConstants.Endpoints.MOVIE_POPULAR)
	suspend fun getPopularMovies(
		@Query(ApiConstants.QueryParamsKeys.PAGE) page: Int,
		@Query(ApiConstants.QueryParamsKeys.LANGUAGE) language: String = ApiConstants.QueryParamsKeys.DEFAULT_LANGUAGE
	): PaginatedResponse<RemoteMovie>
}
