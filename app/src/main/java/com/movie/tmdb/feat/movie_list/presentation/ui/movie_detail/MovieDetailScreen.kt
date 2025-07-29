package com.movie.tmdb.feat.movie_list.presentation.ui.movie_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.compose.SubcomposeAsyncImage
import com.movie.tmdb.R
import com.movie.tmdb.core.util.ImageUrlBuilder
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import com.movie.tmdb.feat.movie_list.navigation.MovieRoute


fun NavGraphBuilder.movieDetailsScreen(
	navController: NavController,
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

	if (movie == null) {
		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			Text(
				text = stringResource(R.string.movie_not_found),
				style = MaterialTheme.typography.bodyLarge
			)
		}
	} else {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.verticalScroll(rememberScrollState())
		) {
			movie.backdropPath?.let { backdropPath ->

				SubcomposeAsyncImage(
					model = ImageUrlBuilder.buildBackdropUrl(backdropPath),
					contentDescription = "Backdrop for ${movie.title}",
					modifier = Modifier
						.fillMaxWidth()
						.clip(shape = RoundedCornerShape(8.dp))
						.height(200.dp),
					contentScale = ContentScale.Fit,
					loading = { CircularProgressIndicator() }
				)
				Spacer(modifier = Modifier.height(16.dp))
			}

			Column(
				modifier = Modifier.padding(horizontal = 16.dp)
			) {
				Text(
					text = movie.title,
					style = MaterialTheme.typography.headlineMedium,
					color = MaterialTheme.colorScheme.onSurface
				)

				Spacer(modifier = Modifier.height(8.dp))

				if (movie.hasRating) {
					Text(
						text = "Rating: ${"%.1f".format(movie.voteAverage)}/10",
						style = MaterialTheme.typography.bodyLarge,
						color = MaterialTheme.colorScheme.onSurfaceVariant
					)
					Spacer(modifier = Modifier.height(16.dp))
				}

				movie.overview?.let { overview ->
					Text(
						text = "Overview",
						style = MaterialTheme.typography.titleMedium,
						color = MaterialTheme.colorScheme.onSurface
					)
					Spacer(modifier = Modifier.height(8.dp))
					Text(
						text = overview,
						style = MaterialTheme.typography.bodyMedium,
						color = MaterialTheme.colorScheme.onSurfaceVariant,
						textAlign = TextAlign.Justify
					)
					Spacer(modifier = Modifier.height(16.dp))
				}
			}
		}
	}
}
