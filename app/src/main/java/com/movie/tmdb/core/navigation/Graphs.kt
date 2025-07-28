package com.movie.tmdb.core.navigation



/**
 * Definitions of all the routes across all the app
 */
sealed class AppGraphs(val route: String) {
	object Movie: AppGraphs("movie_graph")
}
