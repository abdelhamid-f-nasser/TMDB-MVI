package com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Reusable error item for load states
 */
@Composable
fun LoadStateErrorItem(
	message: String,
	buttonText: String = "Retry",
	onRetry: () -> Unit,
) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = message,
			color = MaterialTheme.colorScheme.error,
			textAlign = TextAlign.Center
		)
		Spacer(modifier = Modifier.height(8.dp))
		Button(onClick = onRetry) {
			Text(buttonText)
		}
	}
}
