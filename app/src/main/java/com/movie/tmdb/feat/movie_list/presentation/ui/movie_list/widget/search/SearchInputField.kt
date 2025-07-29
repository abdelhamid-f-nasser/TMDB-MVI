package com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.widget.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun SearchInputField(
	modifier: Modifier,
	query: String?,
	onQueryChanged: (String) -> Unit,
	onClearIconClicked: () -> Unit
) {
	val shape = MaterialTheme.shapes.large
	BasicTextField(
		value = query ?: "",
		textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
		onValueChange = onQueryChanged,
		modifier = modifier
			.fillMaxWidth()
			.wrapContentHeight()
			.clip(shape)
			.padding(vertical = 8.dp, horizontal = 12.dp ),
		singleLine = true,
		cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
		decorationBox = { innerTextField ->
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.height(IntrinsicSize.Max)
					.background(MaterialTheme.colorScheme.surface, shape)
					.padding(8.dp),
			) {
				SearchIcon()
				Box(
					modifier = Modifier
						.weight(1f)
						.fillMaxHeight(),
					contentAlignment = CenterStart
				) {
					innerTextField()
					if (query?.isEmpty() == true)
						Text(
							text = "search",
							color = MaterialTheme.colorScheme.outline,
							style = MaterialTheme.typography.labelMedium,
						)
				}

				if (query?.isNotEmpty() == true) {
					ClearIcon(onClearIconClicked)
				}
			}
		}
	)

}
@Composable
private fun SearchIcon() {
	Icon(
		imageVector = Icons.Default.Search,
		null,
		tint = MaterialTheme.colorScheme.outline,
		modifier = Modifier.padding(horizontal = 4.dp)
	)
}

@Composable
private fun ClearIcon(onClearIconClicked: () -> Unit) {
	IconButton(
		onClick = onClearIconClicked,
		modifier = Modifier
			.padding(horizontal = 4.dp)
			.size(24.dp)
	) {
		Icon(
			imageVector = Icons.Default.Close,
			contentDescription = null,
			tint = MaterialTheme.colorScheme.outline
		)
	}
}
