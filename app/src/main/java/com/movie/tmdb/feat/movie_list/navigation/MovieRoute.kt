package com.movie.tmdb.feat.movie_list.navigation

/**
 * Definitions of all the routes across the movie feature
 */
sealed class MovieRoute(val route: String) {
	object MovieList : MovieRoute("movie_list")
	object MovieDetails : MovieRoute("movie_details/{movieId}") {
		fun createRoute(movieId: String) = "movie_details/${movieId}"
	}
}
