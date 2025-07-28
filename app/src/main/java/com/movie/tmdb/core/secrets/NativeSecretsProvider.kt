package com.movie.tmdb.core.secrets

import javax.inject.Inject

/**
 * Delegates secrets retrieval to [NativeSecrets] which is a native secrets provider.
 */
class NativeSecretsProvider @Inject constructor(
	private val nativeSecrets: NativeSecrets
): SecretsProvider {
	override fun getTmdbApiKey() = nativeSecrets.apiFromJNI()
}
