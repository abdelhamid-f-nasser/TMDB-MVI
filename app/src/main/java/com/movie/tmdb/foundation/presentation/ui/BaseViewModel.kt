package com.movie.tmdb.foundation.presentation.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Base class for all ViewModels implementing the MVI pattern.
 *
 * Provides core functionality for state management and intent handling.
 * Effects are handled via composition with [EffectManager].
 *
 *
 *
 * Type Parameters:
 * @param State The UI state type for this ViewModel
 * @param Intent The intent type for this ViewModel
 *
 * Key Features:
 * - Immutable state management with StateFlow
 * - Composition-ready for effects
 *
 * Usage:
 * ```kotlin
 * class MovieListViewModel @Inject constructor(
 *     private val getMoviesUseCase: GetMoviesUseCase
 * ) : BaseViewModel<MovieListState, MovieListIntent>() {
 *
 *     // Compose effects when needed
 *     private val effectManager = EffectManager<MovieListEffect>()
 *     val effect = effectManager.effect
 *
 *     override fun getInitialState() = MovieListState.Initial
 *
 *     override fun handleIntent(intent: MovieListIntent) {
 *         when (intent) {
 *             is MovieListIntent.LoadMovies -> loadMovies()
 *             // Handle other intents...
 *         }
 *     }
 * }
 * ```
 */
abstract class BaseViewModel<State : UiState, Intent : UiIntent>(
	initialState: State
) : ViewModel() {

	/**
	 * Internal mutable state holder.
	 * Protected to allow subclasses to update state via updateState().
	 */
	private val _uiState = MutableStateFlow(initialState)

	/**
	 * Public read-only state flow for UI observation.
	 *
	 * UI components should collect this flow to react to state changes.
	 * State is immutable and updates should only happen via handleIntent().
	 */
	val uiState = _uiState.asStateFlow()




	/**
	 * Provides the initial state for this ViewModel.
	 *
	 * Called once during ViewModel creation to set up the initial state.
	 * Should return a valid default state with sensible defaults.
	 *
	 * @return The initial state for this ViewModel
	 */
	abstract fun getInitialState(): State

	/**
	 * Handles incoming intents from the UI layer.
	 *
	 * This is the single entry point for all user actions and system events.
	 * Implementation should use when expressions to handle different intent types.
	 *
	 * Thread Safety: This method may be called from different threads,
	 * but state updates via updateState() are thread-safe.
	 *
	 * @param intent The intent to handle
	 */
	abstract fun handleIntent(intent: Intent)

	/**
	 * Thread-safe state update mechanism.
	 *
	 * Provides a reducer-style API for updating state immutably.
	 * The reducer function receives the current state and should return the new state.
	 *
	 * Example:
	 * ```kotlin
	 * updateState {
	 *     copy(
	 *         isLoading = false,
	 *         movies = newMovies
	 *     )
	 * }
	 * ```
	 *
	 * @param reducer Function that takes current state and returns new state
	 */
	protected fun updateState(reducer: State.() -> State) {
		val newState = _uiState.value.reducer()
		if(newState != _uiState.value) {
			_uiState.value = newState
		}
	}
}
