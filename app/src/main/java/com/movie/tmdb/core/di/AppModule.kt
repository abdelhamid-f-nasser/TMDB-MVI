package com.movie.tmdb.core.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Main Hilt module for the application.
 *
 * This module serves as the entry point for all core application dependencies.
 * It includes all the core modules to ensure proper dependency resolution.
 */
@Module(
	includes = [
		NetworkModule::class,
		SecretsModule::class,
	]
)
@InstallIn(SingletonComponent::class)
object AppModule {
	// All dependencies are provided by included modules
}
