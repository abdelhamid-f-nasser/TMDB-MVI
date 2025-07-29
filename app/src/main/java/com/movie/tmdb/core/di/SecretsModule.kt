package com.movie.tmdb.core.di

import com.movie.tmdb.core.secrets.NativeSecretsProvider
import com.movie.tmdb.core.secrets.SecretsProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module that provides secrets-related dependencies.
 *
 * Configures the secure JNI-based API key provider.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class SecretsModule {

	/**
	 * Binds the native secrets provider implementation to the interface.
	 */
	@Binds
	@Singleton
	abstract fun bindSecretsProvider(
		nativeSecretsProvider: NativeSecretsProvider,
	): SecretsProvider
}
