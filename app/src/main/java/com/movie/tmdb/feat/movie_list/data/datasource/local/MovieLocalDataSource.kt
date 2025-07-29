package com.movie.tmdb.feat.movie_list.data.datasource.local


import androidx.paging.PagingSource
import com.movie.tmdb.feat.movie_list.data.datasource.local.model.LocalMovie
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {

	suspend fun insertAllMovies(movies: List<LocalMovie>)
	fun getPopularMovies(): PagingSource<Int, LocalMovie>
	suspend fun getMovieByRemoteId(remoteMovieId: String): Flow<LocalMovie>
	suspend fun getMovieById(movieId: Int): Flow<LocalMovie>
	suspend fun clearAllMovies()
	suspend fun insertAndDeleteOldMovies(movies: List<LocalMovie>)
}
