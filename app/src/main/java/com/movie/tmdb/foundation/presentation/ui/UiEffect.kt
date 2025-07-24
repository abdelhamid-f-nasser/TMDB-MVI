package com.movie.tmdb.foundation.presentation.ui

/**
 * All side effects in the application.
 *
 * Represents one-time events that should happen as a result of state changes,
 * such as navigation, showing dialogs, or triggering system actions.
 *
 * Key Characteristics:
 * - Should be consumed after handling to prevent duplicate execution
 * - Used for actions that don't belong in state (navigation, toasts, etc.)
 * - Should be optional - not all ViewModels need effects
 *
 * Example:
 * ```kotlin
 * sealed interface MovieListEffect : UiEffect {
 *     data class NavigateToDetailScreen(val movieId: Int) : MovieListEffect
 *     data class ShowError(val message: String) : MovieListEffect
 *     data class ShowSnackbar(val message: String) : MovieListEffect
 * }
 * ```
 */
interface UiEffect {
}
