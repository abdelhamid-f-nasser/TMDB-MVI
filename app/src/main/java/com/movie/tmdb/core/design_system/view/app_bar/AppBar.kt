package com.movie.tmdb.core.design_system.view.app_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.movie.tmdb.R
import com.movie.tmdb.feat.movie_list.navigation.MovieRoute
import java.net.URLDecoder


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppBar(
	navController: NavHostController,
) {

	val navBackStackEntry by navController.currentBackStackEntryAsState()
	TopAppBar(
		title = {
			Text(getAppBarTitle(navBackStackEntry))
		},
		navigationIcon = {
			if (shouldShowBackButton(navBackStackEntry?.destination?.route)) {
				IconButton(onClick = { navController.navigateUp() }) {
					Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.back))
				}
			}
		}
	)
}

fun shouldShowBackButton(route: String?): Boolean {
	return when {
		route == MovieRoute.MovieDetails.route -> true
		else -> false
	}
}

@Composable
private fun getAppBarTitle(navBackStackEntry: NavBackStackEntry?): String {
	val route = navBackStackEntry?.destination?.route
	val arguments = navBackStackEntry?.arguments

	return when {
		route?.startsWith("movie_details") == true -> {
			arguments?.getString("movieName")?.let { movieName ->
				URLDecoder.decode(movieName, "UTF-8")
			} ?: stringResource(R.string.movie_details)
		}
		route == MovieRoute.MovieList.route -> stringResource(R.string.popular_movies)

		else -> stringResource(R.string.app_name)
	}
}
