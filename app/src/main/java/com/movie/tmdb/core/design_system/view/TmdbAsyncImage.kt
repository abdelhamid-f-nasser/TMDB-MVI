package com.movie.tmdb.core.design_system.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

/**
 * A reusable TMDB image component with consistent loading states and styling.
 *
 * @param model The image URL or resource to load
 * @param contentDescription Description for accessibility
 * @param modifier Modifier to apply to the image
 * @param contentScale How the image should be scaled within its bounds
 * @param shape Shape to clip the image to (defaults to RoundedCornerShape(8.dp))
 * @param showLoadingIndicator Whether to show a loading indicator (defaults to true)
 */
@Composable
fun TmdbAsyncImage(
	model: Any?,
	contentDescription: String?,
	modifier: Modifier = Modifier,
	contentScale: ContentScale = ContentScale.Crop,
	shape: Shape = RoundedCornerShape(8.dp),
	showLoadingIndicator: Boolean = true
) {
	SubcomposeAsyncImage(
		model = model,
		contentDescription = contentDescription,
		modifier = modifier
			.clip(shape),
		contentScale = contentScale,
		loading = {
			if (showLoadingIndicator) {
				Box(
					modifier = Modifier.fillMaxSize(),
					contentAlignment = Alignment.Center
				) {
					CircularProgressIndicator()
				}
			}
		}
	)
}
