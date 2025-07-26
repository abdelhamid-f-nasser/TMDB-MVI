package com.movie.tmdb.core.constants

/**
 * Constants related to API configuration and endpoints.
 */
object ApiConstants {
    const val TMDB_BASE_URL_V3 = "https://api.themoviedb.org/3"

    object Endpoints {
        const val MOVIE_POPULAR = "movie/popular"
    }

    object QueryParams {
        const val PAGE = "page"
        const val LANGUAGE = "language"
        const val DEFAULT_LANGUAGE = "en-US"
    }

    object Pagination {
        const val DEFAULT_PAGE_SIZE = 20
        private const val INITIAL_PAGE_MULTIPLIER = 2
		const val INITIAL_LOAD_SIZE = DEFAULT_PAGE_SIZE * INITIAL_PAGE_MULTIPLIER
        const val START_PAGE_NUMBER = 1
    }
}
