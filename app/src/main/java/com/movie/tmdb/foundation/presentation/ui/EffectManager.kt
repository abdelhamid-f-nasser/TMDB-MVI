package com.movie.tmdb.foundation.presentation.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Manages one-time UI effects using StateFlow with consumption pattern.
 *
 * This class handles the emission and consumption of effects that should only
 * be executed once, such as navigation events, error dialogs, or snackbars.
 *
 * Note: not all ViewModels need effects
 *
 * How to Use:
 * 1. Emit effect when action should occur
 * 2. UI collects and handles the effect
 * 3. UI calls consume() to prevent re-execution
 *
 * @param Effect The type of effects this manager handles
 *
 * Example:
 * ```kotlin
 * class MyViewModel : BaseViewModel<State, Intent>() {
 *     private val effectManager = EffectManager<MyEffect>()
 *     val effect = effectManager.effect
 *
 *     private fun handleSomeAction() {
 *         effectManager.emit(MyEffect.ShowSuccess("Action completed"))
 *     }
 *
 *     fun consumeEffect() = effectManager.consume()
 * }
 * ```
 */
class EffectManager<Effect : UiEffect> {

	/**
	 * Internal StateFlow that holds the current effect.
	 * Uses nullable type to represent "no effect" state.
	 */
	private val _effect = MutableStateFlow<Effect?>(null)

	/**
	 * Public StateFlow for UI to observe effects.
	 *
	 * UI should collect this flow and handle effects, then call consume()
	 * to prevent the same effect from being handled multiple times.
	 */
	val effect = _effect.asStateFlow()

	/**
	 * Emits a new effect to be handled by the UI.
	 *
	 * This will overwrite any existing effect, so effects should be
	 * consumed promptly by the UI layer.
	 *
	 * @param effect The effect to emit
	 */
	fun emit(effect: Effect) {
		_effect.value = effect
	}

	/**
	 * Consumes the current effect by setting it to null.
	 *
	 * This should be called by the UI after handling an effect to prevent
	 * the same effect from being triggered again on recomposition.
	 *
	 * Best Practice: Always call this after handling an effect in LaunchedEffect.
	 */
	fun consume() {
		_effect.value = null
	}
}
