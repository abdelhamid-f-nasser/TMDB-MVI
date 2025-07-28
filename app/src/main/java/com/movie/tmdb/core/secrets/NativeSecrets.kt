package com.movie.tmdb.core.secrets

import javax.inject.Inject

/**
 * Facilitates secure communication of secrets from the NDK (C++) to Kotlin,
 * while implementing hardening measures to increase resistance against reverse engineering attacks.
 */
class NativeSecrets @Inject constructor() {
	companion object {
		init {
			System.loadLibrary("tmdb")
		}
	}

	external fun apiFromJNI(): String
}
