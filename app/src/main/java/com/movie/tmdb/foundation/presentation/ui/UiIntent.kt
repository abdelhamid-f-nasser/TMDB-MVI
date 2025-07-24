package com.movie.tmdb.foundation.presentation.ui


/**
 * Marker interface for all UI states in the application.
 *
 * Represents the complete state of a screen at any given moment.
 * Should be immutable and contain all data needed to render the UI.
 *
 * Best Practices:
 * - Use data classes for concrete implementations
 * - Include computed properties for derived state
 * - Provide meaningful defaults and Initial companion objects
 * - Keep state flat when possible to avoid deep nesting
 *
 * Example:
 * ```kotlin
 * data class MovieListState(
 *     val movies: List<Movie> = emptyList(),
 *     val isLoading: Boolean = false,
 *     val searchQuery: String = ""
 * ) : UiState {
 *
 *     // Computed properties prevent logic in UI layer
 *     val hasMovies: Boolean get() = movies.isNotEmpty()
 *     val isSearching: Boolean get() = searchQuery.isNotEmpty()
 *
 *     companion object {
 *         val Initial = MovieListState()
 *     }
 * }
 * ```
 */
interface UiIntent {
}
