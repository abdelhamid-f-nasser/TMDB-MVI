package com.movie.tmdb.feat.movie_list.data.datasource.remote

import com.movie.tmdb.core.network.model.PaginatedResponse
import com.movie.tmdb.feat.movie_list.data.datasource.remote.model.RemoteMovie
import javax.inject.Inject

class MovieRetrofitRemoteDataSource @Inject constructor(
	private val tmdbService: TmdbService,
) : MovieRemoteDataSource {

	override suspend fun getPopularMovies(
		page: Int,
	): PaginatedResponse<RemoteMovie> {
		return tmdbService.getPopularMovies(
			page = page,
		)
	}
}
