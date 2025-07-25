package com.movie.tmdb.foundation.presentation.ui.mvi

/**
 * Complete MVI contract that combines all three core concerns
 *
 * It handles State, Intent, and Effect
 *
 * Although some ViewModels may not have Effects, We have them included for consistent architecture.
 */
interface MviManager<State, Intent, Effect> :
	StateManager<State>,
	IntentProcessor<Intent>,
	EffectManager<Effect>
