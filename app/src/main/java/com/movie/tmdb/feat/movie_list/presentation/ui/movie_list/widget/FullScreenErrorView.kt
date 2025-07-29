package com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.movie.tmdb.R

@Composable
fun FullScreenErrorView(
	onRetry: () -> Unit,
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Image(
			painter = painterResource(id = R.drawable.search),
			contentDescription = "Error loading movies",
			modifier = Modifier.size(120.dp)
		)
		Spacer(modifier = Modifier.height(16.dp))
		Text(
			text = "Failed to load movies",
			style = MaterialTheme.typography.headlineSmall,
			textAlign = TextAlign.Center
		)
		Spacer(modifier = Modifier.height(8.dp))
		Text(
			text = "Please check your internet connection and try again",
			style = MaterialTheme.typography.bodyMedium,
			textAlign = TextAlign.Center,
			color = MaterialTheme.colorScheme.onSurfaceVariant
		)
		Spacer(modifier = Modifier.height(24.dp))
		Button(onClick = onRetry) {
			Text("Retry")
		}
	}
}
