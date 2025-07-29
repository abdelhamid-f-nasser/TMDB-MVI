package com.movie.tmdb.foundation.presentation.ui.delegates

import com.movie.tmdb.foundation.presentation.ui.mvi.StateManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Default implementation of [StateManager] for state delegation in MVI.
 *
 * Intended for use as a delegate in MVI architectures, providing state management via Kotlin delegation.
 *
 * @param State The type representing the UI state.
 * @property initialState The initial state value.
 */
class DefaultStateManager<State>(
	initialState: State,
) : StateManager<State> {
	private val _uiState = MutableStateFlow(initialState)

	override val uiState: StateFlow<State> = _uiState

	override fun updateState(reducer: State.() -> State) {
		_uiState.value = _uiState.value.reducer()
	}

	override fun getCurrentState(): State {
		return _uiState.value
	}
}
