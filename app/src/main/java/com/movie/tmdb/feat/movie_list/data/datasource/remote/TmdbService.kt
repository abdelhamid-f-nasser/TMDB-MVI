package com.movie.tmdb.feat.movie_list.data.datasource.remote

import com.movie.tmdb.core.constants.ApiConstants
import com.movie.tmdb.core.network.model.PaginatedResponse
import com.movie.tmdb.feat.movie_list.data.datasource.remote.model.RemoteMovie
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {

	@GET(ApiConstants.Endpoints.MOVIE_POPULAR)
	fun getPopularMovies(
		@Query(ApiConstants.QueryParams.PAGE) page: Int,
		@Query(ApiConstants.QueryParams.LANGUAGE) language: String = ApiConstants.QueryParams.DEFAULT_LANGUAGE
	): PaginatedResponse<RemoteMovie>
}
