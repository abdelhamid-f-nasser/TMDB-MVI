package com.movie.tmdb.core.constants

/**
 * Constants related to API configuration and endpoints.
 */
object ApiConstants {
	const val TMDB_BASE_URL_V3 = "https://api.themoviedb.org/3/"
	const val TMDB_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"

    object Endpoints {
        const val MOVIE_POPULAR = "movie/popular"
        const val SEARCH_MOVIE = "search/movie"
    }

	object QueryParamsKeys {
		const val API_KEY = "api_key"
		const val PAGE = "page"
		const val SEARCH_QUERY = "query"
		const val LANGUAGE = "language"
		const val DEFAULT_LANGUAGE = "en-US"
	}

	object Pagination {
		const val DEFAULT_PAGE_SIZE = 20
		private const val INITIAL_PAGE_MULTIPLIER = 2
		const val INITIAL_LOAD_SIZE = DEFAULT_PAGE_SIZE * INITIAL_PAGE_MULTIPLIER
		const val START_PAGE_NUMBER = 1
	}

	object ImageSizes {
		const val POSTER_W185 = "w185"
		const val POSTER_W342 = "w342"
		const val POSTER_W500 = "w500"
		const val POSTER_W780 = "w780"
		const val BACKDROP_W300 = "w300"
		const val BACKDROP_W780 = "w780"
		const val BACKDROP_W1280 = "w1280"
		const val BACKDROP_ORIGINAL = "original"
	}
}
