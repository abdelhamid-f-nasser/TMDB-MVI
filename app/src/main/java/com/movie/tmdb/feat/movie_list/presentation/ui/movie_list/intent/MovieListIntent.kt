package com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.intent

import com.movie.tmdb.feat.movie_list.domain.model.Movie

/**
 * User intents/actions for the Movie List screen.
 *
 * Represents all possible user interactions and system events.
 */
sealed interface MovieListIntent {

	/**
	 * Intent to load movies initially.
	 * Triggered when screen is first displayed.
	 */
	data object LoadMovies : MovieListIntent

	/**
	 * Intent to retry loading movies after an error.
	 * Triggered when user taps retry button.
	 */
	data object RetryLoading : MovieListIntent

	/**
	 * Intent to refresh the movie list.
	 * Triggered by pull-to-refresh or refresh button.
	 */
	data object RefreshMovies : MovieListIntent

	/**
	 * Intent when a movie is clicked.
	 * Triggered when user taps on a movie item.
	 *
	 * @param movie the movie object containing all it's info
	 */
	data class MovieClicked(val movie: Movie) : MovieListIntent
}
