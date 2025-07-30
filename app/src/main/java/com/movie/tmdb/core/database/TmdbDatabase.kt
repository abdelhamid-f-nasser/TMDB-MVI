package com.movie.tmdb.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.movie.tmdb.feat.movie_list.data.datasource.local.MoviesDao
import com.movie.tmdb.feat.movie_list.data.datasource.local.model.LocalMovie
import com.movie.tmdb.feat.movie_list.data.datasource.local.model.converter.LocalMovieConvertors

@Database(
	entities = [LocalMovie::class],
	version = 1,
	exportSchema = false
)
@TypeConverters(
	LocalMovieConvertors::class
)
abstract class TmdbDatabase: RoomDatabase() {
	abstract fun moviesDao(): MoviesDao

	companion object {
		private const val DATABASE_NAME = "tmdbApp-database"

		fun buildDatabase(context: Context) =
			Room.databaseBuilder(context, TmdbDatabase::class.java, DATABASE_NAME)
				.fallbackToDestructiveMigration(false)
				.build()
	}
}
