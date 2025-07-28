package com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.movie.tmdb.core.design_system.view.TmdbAsyncImage
import com.movie.tmdb.core.util.ImageUrlBuilder
import com.movie.tmdb.feat.movie_list.domain.model.Movie


@Composable
fun MovieItem(
	movie: Movie,
	onClick: (movie: Movie) -> Unit,
) {
	Card(
		modifier = Modifier.clickable { onClick(movie) },
	) {
		Box(
			modifier = Modifier.fillMaxSize()
		) {
			// Background poster image
			TmdbAsyncImage(
				model = ImageUrlBuilder.buildPosterUrl(movie.posterPath),
				contentDescription = "Poster for ${movie.title}",
				modifier = Modifier.fillMaxSize().defaultMinSize(minHeight = 250.dp)
			)

			Box(
				modifier = Modifier
					.align(Alignment.BottomStart)
					.fillMaxWidth()
					.padding(8.dp)
					.background(
						color = MaterialTheme.colorScheme.background.copy(alpha = 0.8f),
						RoundedCornerShape(4.dp)
					)
					.padding(8.dp)
			) {
				Column {
					Text(
						text = movie.title,
						style = MaterialTheme.typography.titleSmall,
						color = MaterialTheme.colorScheme.onBackground,
					)
					Spacer(modifier = Modifier.height(4.dp))
					Text(
						text = "Rating: ${"%.1f".format(movie.voteAverage ?: 0.0)}",
						style = MaterialTheme.typography.bodySmall,
						color = MaterialTheme.colorScheme.onBackground
					)
				}
			}
		}
	}
}
