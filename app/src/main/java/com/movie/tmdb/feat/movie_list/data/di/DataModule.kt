package com.movie.tmdb.feat.movie_list.data.di

import com.movie.tmdb.feat.movie_list.data.datasource.local.MovieLocalDataSource
import com.movie.tmdb.feat.movie_list.data.datasource.local.MovieRoomLocalDataSource
import com.movie.tmdb.feat.movie_list.data.datasource.remote.MovieRemoteDataSource
import com.movie.tmdb.feat.movie_list.data.datasource.remote.MovieRetrofitRemoteDataSource
import com.movie.tmdb.feat.movie_list.data.datasource.remote.TmdbService
import com.movie.tmdb.feat.movie_list.data.repository_impl.DefaultMovieRepository
import com.movie.tmdb.feat.movie_list.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Hilt module that provides data layer dependencies for the movie list feature.
 *
 * Provides API services, data sources, and repository implementations.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

	/**
	 * Binds the default repository implementation to the repository interface.
	 */
	@Binds
	@Singleton
	abstract fun bindMovieRepository(
		defaultMovieRepository: DefaultMovieRepository,
	): MovieRepository

	/**
	 * Binds the Retrofit implementation to the remote data source interface.
	 */
	@Binds
	@Singleton
	abstract fun bindMovieRemoteDataSource(
		movieRetrofitRemoteDataSource: MovieRetrofitRemoteDataSource,
	): MovieRemoteDataSource

	/**
	 * Binds the Room implementation to the local data source interface.
	 */
	@Binds
	@Singleton
	abstract fun bindMovieLocalDataSource(
		movieRoomLocalDataSource: MovieRoomLocalDataSource
	): MovieLocalDataSource

	companion object {
		/**
		 * Provides the TMDB API service using Retrofit.
		 */
		@Provides
		@Singleton
		fun provideTmdbService(retrofit: Retrofit): TmdbService {
			return retrofit.create(TmdbService::class.java)
		}
	}
}
