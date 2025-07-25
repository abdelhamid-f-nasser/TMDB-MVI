package com.movie.tmdb.foundation.presentation.ui.mvi

import kotlinx.coroutines.flow.StateFlow

/**
 * Contract for state management delegation in MVI architecture.
 * Handles immutable state updates and exposes state as a StateFlow for UI observation.
 *
 * @param State The type representing the UI state.
 *
 * Usage:
 * Implement this interface to provide state management logic for your ViewModel or delegate.
 */
interface StateManager<State> {

	/**
	 * Public read-only state flow for UI observation.
	 *
	 * UI components should collect this flow to react to state changes.
	 * State is immutable and updates should only happen through the [updateState] method.
	 */
	val uiState: StateFlow<State>


	/**
	 * Updates the state with the provided reducer function.
	 *
	 * Provides a reducer-style API for updating state immutably.
	 * The reducer function receives the current state and should return the new state.
	 *
	 * Example:
	 * ```kotlin
	 * updateState {
	 *     copy(count = count + 1)
	 * }
	 * ```
	 *
	 * @param reducer Function that takes current state and returns new state
	 */
	fun updateState(reducer: State.() -> State)

	/**
	 * Returns the current state.
	 *
	 * @return The current state value.
	 */
	fun getCurrentState(): State
}
