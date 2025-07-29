package com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.movie.tmdb.R
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.intent.MovieListIntent

/**
 * Movie list composable with pagination support.
 * Loading states are handled by the parent screen through UI state.
 */
@Composable
fun MovieListView(
	movies: LazyPagingItems<Movie>,
	state: LazyGridState,
	onError: (String) -> Unit,
	onRetry: () -> Unit,
	onIntent: (MovieListIntent) -> Unit,
	onMovieClick: (Movie) -> Unit,
) {
	val gridItemSpan = 2;
	LazyVerticalGrid(
		state = state,
		columns = GridCells.Fixed(gridItemSpan),
		contentPadding = PaddingValues(12.dp),
		horizontalArrangement = Arrangement.spacedBy(12.dp),
		verticalArrangement = Arrangement.spacedBy(12.dp)
	) {
		items(
			movies.itemCount,
			key = { index -> "movie_$index" }
		) { index ->
			movies[index]?.let { movie ->
				MovieItem(
					movie = movie,
					onClick = { movie -> onMovieClick(movie) }
				)
			}
		}

		when (movies.loadState.append) {
			is LoadState.Loading -> {
				item {
					Box(
						modifier = Modifier
							.fillMaxWidth()
							.padding(16.dp),
						contentAlignment = Alignment.Center
					) {
						CircularProgressIndicator()
					}
				}
			}

			is LoadState.Error -> {
				item {
					val error = stringResource(R.string.failed_to_load_more_movies)
					onError(error)
					LoadStateErrorItem(
						message = error,
						buttonText = "Retry",
						onRetry = {
							movies.retry()
							onRetry()
						}
					)
				}
			}

			else -> {}
		}
	}
}
