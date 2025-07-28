package com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import com.movie.tmdb.feat.movie_list.domain.usecase.GetMoviesUseCase
import com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.effect.MovieListEffect
import com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.intent.MovieListIntent
import com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.state.MovieListState
import com.movie.tmdb.foundation.presentation.ui.delegates.DefaultMviManager
import com.movie.tmdb.foundation.presentation.ui.mvi.MviManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Movie List screen using MVI architecture.
 *
 * Handles user intents, manages UI state, and coordinates with the domain layer.
 */
@HiltViewModel
class MovieListViewModel @Inject constructor(
	private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

	private val mviManager: MviManager<MovieListState, MovieListIntent, MovieListEffect> by lazy {
		DefaultMviManager(
			initialState = MovieListState(),
			onIntent = { intent -> handleIntent(intent) }
		)
	}

	// Expose MVI properties
	val uiState = mviManager.uiState
	val effect = mviManager.effect

	// Public intent handler
	fun handleIntent(intent: MovieListIntent) {
		when (intent) {
			is MovieListIntent.LoadMovies -> loadMovies()
			is MovieListIntent.RetryLoading -> retryLoading()
			is MovieListIntent.RefreshMovies -> refreshMovies()
			is MovieListIntent.MovieClicked -> onMovieClicked(intent.movie)
		}
	}

	fun consumeEffect() = mviManager.consumeEffect()

	/**
	 * Loads the initial list of movies.
	 */
	private fun loadMovies(isRefreshing: Boolean = false) {
		viewModelScope.launch {
			try {
				val moviesFlow = getMoviesUseCase().cachedIn(viewModelScope)
				mviManager.updateState {
					copy(
						moviesFlow,
					)
				}
			} catch (exception: Exception) {
				Log.e("MovieListViewModel", "Error loading movies: ${exception.message}")
			}
		}
	}

	/**
	 * Retries loading movies after an error.
	 */
	private fun retryLoading() {
		loadMovies()
	}

	/**
	 * Refreshes the movie list.
	 */
	private fun refreshMovies() {
		loadMovies(isRefreshing = true)
	}

	/**
	 * Handles movie item click.
	 */
	private fun onMovieClicked(movie: Movie) {
		mviManager.emitEffect(MovieListEffect.NavigateToMovieDetails(movie))
	}
}
