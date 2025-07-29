package com.movie.tmdb.feat.movie_list.presentation.ui.movie_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.movie.tmdb.R
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import com.movie.tmdb.feat.movie_list.navigation.MovieRoute
import com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.effect.MovieListEffect
import com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.intent.MovieListIntent
import com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.state.MovieListState
import com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.viewmodel.MovieListViewModel
import com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.widget.FullScreenErrorView
import com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.widget.MovieListView
import com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.widget.search.SearchInputField

fun NavGraphBuilder.movieListScreen(
	onMovieClick: (Movie) -> Unit,
	onShowSnackBar: suspend (String) -> Unit,
) {
	composable(route = MovieRoute.MovieList.route) {
		MovieListScreen(
			onMovieClick = onMovieClick,
			onShowSnackBar = onShowSnackBar
		)
	}
}

/**
 * Movie List screen composable.
 *
 * Displays a paginated list of popular movies with loading states and error handling.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieListScreen(
	onMovieClick: (Movie) -> Unit,
	onShowSnackBar: suspend (String) -> Unit,
	viewModel: MovieListViewModel = hiltViewModel(),
) {
	val uiState: MovieListState by viewModel.uiState.collectAsStateWithLifecycle()
	val movies = uiState.moviesFlow?.collectAsLazyPagingItems()
	val listState = rememberLazyGridState()
	val refreshState = rememberPullToRefreshState()

	val onQueryChange = { query: String ->
		viewModel.handleIntent(
			MovieListIntent.SearchMovies(query
			)
		)
	}

	val onClearSearchClick = {
		viewModel.handleIntent(
			MovieListIntent.SearchMovies.clear
		)
	}

	val isInitialLoading = movies?.loadState?.refresh is LoadState.Loading && movies.itemCount == 0
	val isRefreshing = movies?.loadState?.refresh is LoadState.Loading

	val shouldShowFullScreenLoading = isInitialLoading

	val shouldShowEmptyScreen = movies?.let { pagingItems ->
		pagingItems.loadState.refresh is LoadState.NotLoading && pagingItems.itemCount == 0
	} ?: false
	val shouldShowFullScreenError = movies?.loadState?.refresh is LoadState.Error

	LaunchedEffect(isRefreshing) {
		if (isRefreshing) {
			listState.scrollToItem(0)
		}
	}

	val searchQuery = uiState.searchQuery


	LaunchedEffect(viewModel) {
		viewModel.effect.collect { effect ->
			when (effect) {
				is MovieListEffect.NavigateToMovieDetails -> {
					onMovieClick(effect.movie)
				}

				null -> {

				}
			}
		}
	}

	LaunchedEffect(movies) {
		movies?.let { pagingItems ->
			when (pagingItems.loadState.refresh) {
				is LoadState.Error -> {
					val errorMessage =
						(pagingItems.loadState.refresh as LoadState.Error).error.message
							?: "Failed to load movies"
					onShowSnackBar(errorMessage)
					viewModel.handleIntent(MovieListIntent.RetryLoading)
				}

				else -> {}
			}
		}
	}

	Column {
		Row(
			modifier = Modifier
				.background(color = MaterialTheme.colorScheme.inverseOnSurface)
				.height(IntrinsicSize.Min)
		) {
			SearchInputField(Modifier.weight(0.85f), searchQuery, onQueryChange, onClearSearchClick)
		}
		PullToRefreshBox(
			isRefreshing = isRefreshing,
			onRefresh = { viewModel.handleIntent(MovieListIntent.RefreshMovies) },
			state = refreshState,
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.Center,
		) {
			when {
				shouldShowFullScreenLoading -> {
					CircularProgressIndicator()
				}

				shouldShowFullScreenError -> {
					FullScreenErrorView(
						onRetry = {
							viewModel.handleIntent(MovieListIntent.RetryLoading)
						}
					)
				}

				shouldShowEmptyScreen -> {
					Column(
						horizontalAlignment = Alignment.CenterHorizontally
					) {
						Image(
							painter = painterResource(id = R.drawable.search),
							contentDescription = "No movies",
							modifier = Modifier.size(120.dp)
						)
						Spacer(modifier = Modifier.height(16.dp))
						Text("No movies found")
					}
				}

				movies != null -> {
					MovieListView(
						movies = movies,
						state = listState,
						onError = { errorMessage ->
							viewModel.handleIntent(MovieListIntent.RetryLoading)
						},
						onRetry = {
							viewModel.handleIntent(MovieListIntent.RetryLoading)
						},
						onIntent = { intent ->
							viewModel.handleIntent(intent)
						},
						onMovieClick = onMovieClick
					)
				}
			}
		}
	}
}
