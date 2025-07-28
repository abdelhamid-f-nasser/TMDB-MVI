package com.movie.tmdb.core.network.model

import com.squareup.moshi.Json

/**
 * Generic data model for paginated API responses.
 *
 * Used for The movie database responses.
 */
data class PaginatedResponse<T>(
    @param:Json(name = "page")
	val page: Int,

	@param:Json(name = "results")
	val results: List<T>,

    @param:Json(name = "total_pages")
	val totalPages: Int,

    @param:Json(name = "total_results")
	val totalResults: Int
)
