package com.movie.tmdb.feat.movie_list.data.datasource.local


import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.movie.tmdb.core.constants.DatabaseConstants
import com.movie.tmdb.feat.movie_list.data.datasource.local.model.LocalMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(movies: List<LocalMovie>)

	@Query(
		"SELECT * FROM ${DatabaseConstants.Table.MOVIE_TABLE_NAME} " +
			"ORDER BY popularity DESC, title ASC"
	)
	fun getPopularMovies(): PagingSource<Int, LocalMovie>

	@Query(
		"SELECT * FROM ${DatabaseConstants.Table.MOVIE_TABLE_NAME} " +
		"WHERE id IS :movieId"
	)
	fun getMovieById(movieId :Int) : Flow<LocalMovie>

	@Query(
		"SELECT * FROM ${DatabaseConstants.Table.MOVIE_TABLE_NAME} " +
			"WHERE remoteId IS :remoteMovieId"
	)
	fun getMovieByRemoteId(remoteMovieId: String): Flow<LocalMovie>

	@Query("DELETE FROM ${DatabaseConstants.Table.MOVIE_TABLE_NAME}")
	suspend fun clearAllMovies()

	@Transaction
	suspend fun insertAndDeleteOldMovies(movies: List<LocalMovie>) {
		clearAllMovies()
		insertAll(movies)
	}
}
