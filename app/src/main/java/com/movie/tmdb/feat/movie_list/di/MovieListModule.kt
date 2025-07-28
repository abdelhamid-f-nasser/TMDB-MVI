package com.movie.tmdb.feat.movie_list.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Main Hilt module for the Movie List feature.
 * 
 * This module serves as the entry point for all movie list feature dependencies.
 * It includes the data and domain modules for this feature.
 * 
 * All dependencies are automatically injectable via @Inject constructors:
 * - GetMoviesUseCase: Domain layer use case
 * - DefaultMovieRepository: Repository implementation  
 * - MoviePagingSource: Pagination data source
 * - MovieRetrofitRemoteDataSource: Remote data source implementation
 * - TmdbService: API service (provided by DataModule)
 */
@Module(
	includes = [
		com.movie.tmdb.feat.movie_list.data.di.DataModule::class,
		com.movie.tmdb.feat.movie_list.domain.di.DomainModule::class
	]
)
@InstallIn(SingletonComponent::class)
object MovieListModule {
	// All dependencies are provided by included modules and @Inject constructors
} 