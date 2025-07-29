package com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.movie.tmdb.core.constants.UiConstants
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import com.movie.tmdb.feat.movie_list.domain.usecase.GetMoviesUseCase
import com.movie.tmdb.feat.movie_list.domain.usecase.SearchMoviesUseCase
import com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.effect.MovieListEffect
import com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.intent.MovieListIntent
import com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.state.MovieListState
import com.movie.tmdb.foundation.presentation.ui.delegates.DefaultMviManager
import com.movie.tmdb.foundation.presentation.ui.mvi.MviManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

/**
 * ViewModel for the Movie List screen using MVI architecture.
 *
 * Handles user intents, manages UI state, and coordinates with the domain layer.
 */
@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class MovieListViewModel @Inject constructor(
	private val getMoviesUseCase: GetMoviesUseCase,
	private val searchMoviesUseCase: SearchMoviesUseCase,
) : ViewModel() {

	private val mviManager: MviManager<MovieListState, MovieListIntent, MovieListEffect> by lazy {
		DefaultMviManager(
			initialState = MovieListState(),
			onIntent = { intent -> processIntent(intent) }
		)
	}

	private val searchQuery = MutableStateFlow("")

	private val refreshTrigger = MutableStateFlow(0)

	private val moviesPagingFlow: Flow<PagingData<Movie>> = combine(
		searchQuery,
		refreshTrigger
	) { query, refreshCount -> Pair(query, refreshCount) }
		.debounce(UiConstants.Search.DEBOUNCE_SEARCH_INPUT)
		.flatMapLatest { (query, _) ->
			if (query.isBlank()) {
				getMoviesUseCase()
			} else {
				searchMoviesUseCase(query)
			}
		}
		.cachedIn(viewModelScope)


	init {
		mviManager.updateState {
			copy(moviesFlow = moviesPagingFlow)
		}
	}

	val uiState = mviManager.uiState
	val effect = mviManager.effect

	fun handleIntent(intent: MovieListIntent) {
		mviManager.handleIntent(intent)
	}

	private fun processIntent(intent: MovieListIntent) {
		when (intent) {
			is MovieListIntent.SearchMovies -> updateSearchQuery(intent.query)
			is MovieListIntent.MovieClicked -> onMovieClicked(intent.movie)
			is MovieListIntent.RefreshMovies -> refreshMovies()
			is MovieListIntent.RetryLoading -> retryLoading()
	}
	}

	private fun updateSearchQuery(query: String) {
		searchQuery.value = query
		mviManager.updateState { copy(searchQuery = query) }
	}

	private fun onMovieClicked(movie: Movie) {
		mviManager.emitEffect(MovieListEffect.NavigateToMovieDetails(movie))
	}

	private fun refreshMovies() {
		refreshTrigger.value++
	}

	private fun retryLoading() {
		refreshTrigger.value++
	}
}
