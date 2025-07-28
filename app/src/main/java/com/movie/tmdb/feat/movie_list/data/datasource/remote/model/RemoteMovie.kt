package com.movie.tmdb.feat.movie_list.data.datasource.remote.model

import com.movie.tmdb.core.network.model.PaginatedResponse
import com.squareup.moshi.Json

/**
 * Alias for a paginated response of remote movie items from the API.
 *
 * Represents the full paginated response for popular movies or similar endpoints,
 * where the API returns a list of movies along with pagination metadata.
 *
 * Example usage:
 * ```
 * val response: RemoteMovieResponse = ... // fetched from API
 * val movies: List<RemoteMovie> = response.results
 * ```
 *
 * @see PaginatedResponse
 */
typealias RemoteMovieResponse = PaginatedResponse<RemoteMovie>

/**
 * Data model for a single movie item from the remote API.
 */
data class RemoteMovie(
	@param:Json(name = "adult")
	val adult: Boolean,

	@param:Json(name = "backdrop_path")
	val backdropPath: String?,

	@param:Json(name = "genre_ids")
	val genreIds: List<Int>,

	@param:Json(name = "id")
	val id: Int,

	@param:Json(name = "original_language")
	val originalLanguage: String,

	@param:Json(name = "original_title")
	val originalTitle: String,

	@param:Json(name = "overview")
	val overview: String,

	@param:Json(name = "popularity")
	val popularity: Double,

	@param:Json(name = "poster_path")
	val posterPath: String?,

	@param:Json(name = "release_date")
	val releaseDate: String,

	@param:Json(name = "title")
	val title: String,

	@param:Json(name = "video")
	val video: Boolean,

	@param:Json(name = "vote_average")
	val voteAverage: Double,

	@param:Json(name = "vote_count")
	val voteCount: Int
)
