package com.movie.tmdb.feat.movie_list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.movie.tmdb.core.navigation.AppGraphs
import com.movie.tmdb.feat.movie_list.presentation.ui.movie_detail.movieDetailsScreen
import com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.movieListScreen

fun NavGraphBuilder.movieGraph(
	navController: NavController,
	onShowSnackBar: suspend (String) -> Unit,
) {
	navigation(
		startDestination = MovieRoute.MovieList.route,
		route = AppGraphs.Movie.route,
	) {
		movieListScreen(
			onMovieClick = { movie ->
				navController.currentBackStackEntry?.savedStateHandle?.set("movie", movie)
				navController.navigate(MovieRoute.MovieDetails.createRoute(movie.id))
			},
			onShowSnackBar = onShowSnackBar
		)

		movieDetailsScreen(
			navController = navController
		)
	}
}
