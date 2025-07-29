package com.movie.tmdb.foundation.presentation.ui.delegates

import com.movie.tmdb.foundation.presentation.ui.mvi.EffectManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Default implementation of [EffectManager] for effect delegation in MVI.
 *
 * Intended for use as a delegate in MVI architectures, providing effect management via Kotlin delegation.
 *
 * @param Effect The type representing one-time UI effects.
 * @property initialEffect The initial effect value (optional).
 */
class DefaultEffectManager<Effect>(
	initialEffect: Effect? = null,
) : EffectManager<Effect> {
	private val _effect = MutableStateFlow(initialEffect)
	override val effect: StateFlow<Effect?> = _effect

	override fun emitEffect(effect: Effect) {
		_effect.value = effect
	}

	override fun consumeEffect() {
		_effect.value = null
	}
}
