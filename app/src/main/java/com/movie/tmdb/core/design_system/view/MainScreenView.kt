package com.movie.tmdb.core.design_system.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.movie.tmdb.core.design_system.theme.TMDBTheme
import com.movie.tmdb.core.design_system.view.app_bar.AppBar
import com.movie.tmdb.core.navigation.AppGraphs
import com.movie.tmdb.feat.movie_list.navigation.movieGraph


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenView() {
	val snackbarHostState = remember { SnackbarHostState() }
	val navController = rememberNavController()

	TMDBTheme {
		Scaffold(
			topBar = { AppBar(navController) },
			snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
		) { contentPadding ->
			NavHost(
				navController = navController,
				startDestination = AppGraphs.Movie.route,
				modifier = Modifier.padding(contentPadding)
			) {

				movieGraph(
					navController,
					onShowSnackBar = { message ->
						snackbarHostState.showSnackbar(
							message = message,
							duration = SnackbarDuration.Short,
						)
					}
				)
			}
		}
	}
}
