package com.movie.tmdb.core.di

import android.content.Context
import com.movie.tmdb.core.database.TmdbDatabase
import com.movie.tmdb.feat.movie_list.data.datasource.local.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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

	@Singleton
	@Provides
	fun provideAppDatabase(@ApplicationContext context: Context): TmdbDatabase {
		return TmdbDatabase.buildDatabase(context)
	}

	@Singleton
	@Provides
	fun provideMoviesDao(tmdbDatabase: TmdbDatabase): MoviesDao {
		return tmdbDatabase.moviesDao()
	}
}
