package com.movie.tmdb.feat.movie_list.data.datasource.local


import androidx.paging.PagingSource
import com.movie.tmdb.feat.movie_list.data.datasource.local.model.LocalMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRoomLocalDataSource @Inject constructor(
	private val moviesDao: MoviesDao,
) : MovieLocalDataSource {
	override suspend fun insertAllMovies(movies: List<LocalMovie>) =
		moviesDao.insertAll(movies)

	override fun getPopularMovies(): PagingSource<Int, LocalMovie> =
		moviesDao.getPopularMovies()

	override suspend fun getMovieById(movieId: Int): Flow<LocalMovie> =
		moviesDao.getMovieById(movieId)

	override suspend fun insertAndDeleteOldMovies(movies: List<LocalMovie>) =
		moviesDao.insertAndDeleteOldMovies(movies)

	override suspend fun getMovieByRemoteId(remoteMovieId: String): Flow<LocalMovie> =
		moviesDao.getMovieByRemoteId(remoteMovieId)

	override suspend fun clearAllMovies()=
		moviesDao.clearAllMovies()
}
