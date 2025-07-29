package com.movie.tmdb.feat.movie_list.domain.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Hilt module that provides domain layer dependencies for the movie list feature.
 *
 * Currently, all use cases use constructor injection with @Inject, so no explicit
 * bindings are needed. This module serves as a placeholder for future domain
 * layer dependencies that might require custom providers.
 */
@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
	// No bindings needed currently - GetMoviesUseCase uses @Inject constructor
	// Add future use case bindings here if needed
}
