package com.movie.tmdb.foundation.presentation.ui.mvi

import kotlinx.coroutines.flow.StateFlow

/**
 * Contract for effect management delegation in MVI architecture.
 * Manages one-time UI effects with a consumption pattern.
 *
 * This interface handles the emission and consumption of effects that should only
 * be executed once, such as navigation events, error dialogs, or snackbars.
 *
 * @param Effect The type representing one-time UI effects.
 *
 * Usage:
 * Implement this interface to provide effect management logic for your ViewModel or delegate.
 */
interface EffectManager<Effect> {
	/**
	 * Public StateFlow for UI to observe effects.
	 *
	 * UI should collect this flow and handle effects, then call [consumeEffect]
	 * to prevent the same effect from being handled multiple times.
	 */
	val effect: StateFlow<Effect?>

	/**
	 * Emits a new effect to be handled by the UI.
	 *
	 * This will overwrite any existing effect, so effects should be
	 * consumed promptly by the UI layer.
	 *
	 * @param effect The effect to emit
	 */
	fun emitEffect(effect: Effect)

	/**
	 * Consumes the current effect.
	 *
	 * This should be called by the UI after handling an effect to prevent
	 * the same effect from being triggered again on recomposition.
	 *
	 * Best Practice: Always call this after handling an effect in LaunchedEffect.
	 */
	fun consumeEffect()
}
