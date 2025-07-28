package com.movie.tmdb.core.secrets

/**
 * Contract for providing sensitive secrets required by the application.
 */
interface SecretsProvider {
	/**
	 * Retrieves the TMDB API key from where it is stored
	 * @return TMDB API key as a String
	 */
	fun getTmdbApiKey(): String
}
