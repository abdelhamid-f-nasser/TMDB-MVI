package com.movie.tmdb.feat.movie_list.domain.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Hilt module that provides domain layer dependencies for the movie list feature.
 *
 * Provides bindings for use case interfaces to their default implementations.
 */
@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
}
