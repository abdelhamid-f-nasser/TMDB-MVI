package com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.effect

import com.movie.tmdb.feat.movie_list.domain.model.Movie

/**
 * One-time UI effects for the Movie List screen.
 *
 * Represents effects that should only happen once, like navigation or showing messages.
 */
sealed interface MovieListEffect {

	/**
	 * Effect to navigate to movie details screen.
	 *
	 * @param movie The object containing all the movie's info
	 */
	data class NavigateToMovieDetails(val movie: Movie) : MovieListEffect
}
