package com.movie.tmdb.foundation.presentation.ui.delegates

import com.movie.tmdb.foundation.presentation.ui.mvi.EffectManager
import com.movie.tmdb.foundation.presentation.ui.mvi.IntentProcessor
import com.movie.tmdb.foundation.presentation.ui.mvi.MviManager
import com.movie.tmdb.foundation.presentation.ui.mvi.StateManager

/**
 * Default implementation of [MviManager] for MVI delegation.
 *
 * Intended for use as a delegate in MVI architectures, providing state, intent, and effect management via Kotlin delegation.
 *
 * @param State The type representing the UI state.
 * @param Intent The type representing user or system intents/actions.
 * @param Effect The type representing one-time UI effects.
 * @property initialState The initial state value.
 * @property onIntent Lambda to handle incoming intents.
 * @property initialEffect The initial effect value (optional). Usually `null`, but can be set to trigger an effect immediately on creation (e.g., onboarding dialog, error message).
 */
class DefaultMviManager<State, Intent, Effect>(
	initialState: State,
	onIntent: (Intent) -> Unit,
	initialEffect: Effect? = null,
) : MviManager<State, Intent, Effect>,
	StateManager<State> by DefaultStateManager(initialState),
	IntentProcessor<Intent> by DefaultIntentProcessor(onIntent),
	EffectManager<Effect> by DefaultEffectManager(initialEffect)
