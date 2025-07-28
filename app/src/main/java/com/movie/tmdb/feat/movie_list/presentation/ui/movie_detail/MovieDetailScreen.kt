package com.movie.tmdb.feat.movie_list.presentation.ui.movie_detail

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import com.movie.tmdb.feat.movie_list.navigation.MovieRoute


fun NavGraphBuilder.movieDetailsScreen(
	navController: NavController
) {
	composable(
		route = MovieRoute.MovieDetails.route,
		arguments = listOf(navArgument("movieId") { type = NavType.StringType })
	) { backStackEntry ->
		val movie = navController.previousBackStackEntry?.savedStateHandle?.get<Movie>("movie")

		MovieDetailsScreen(
			movie = movie,
		)
	}
}


@Composable
fun MovieDetailsScreen(
	movie: Movie?,
) {


}
