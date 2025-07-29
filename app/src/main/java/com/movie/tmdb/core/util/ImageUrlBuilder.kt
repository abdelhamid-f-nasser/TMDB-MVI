package com.movie.tmdb.core.util

import com.movie.tmdb.core.constants.ApiConstants

/**
 * Utility class for building TMDB image URLs.
 */
object ImageUrlBuilder {

	/**
	 * Builds a poster image URL for the given poster path and size.
	 *
	 * @param posterPath The poster path from the API (e.g., "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg")
	 * @param size The image size (e.g., ApiConstants.ImageSizes.POSTER_W500)
	 * @return The complete image URL or null if posterPath is null
	 */
	fun buildPosterUrl(
		posterPath: String?,
		size: String = ApiConstants.ImageSizes.POSTER_W500,
	): String? {
		return posterPath?.let { path ->
			"${ApiConstants.TMDB_IMAGE_BASE_URL}$size$path"
		}
	}

	/**
	 * Builds a backdrop image URL for the given backdrop path and size.
	 *
	 * @param backdropPath The backdrop path from the API (e.g., "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg")
	 * @param size The image size (e.g., ApiConstants.ImageSizes.BACKDROP_W780)
	 * @return The complete image URL or null if backdropPath is null
	 */
	fun buildBackdropUrl(
		backdropPath: String?,
		size: String = ApiConstants.ImageSizes.BACKDROP_W780,
	): String? {
		return backdropPath?.let { path ->
			"${ApiConstants.TMDB_IMAGE_BASE_URL}$size$path"
		}
	}
}
