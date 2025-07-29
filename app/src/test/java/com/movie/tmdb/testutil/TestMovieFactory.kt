package com.movie.tmdb.testutil

import com.movie.tmdb.feat.movie_list.data.datasource.remote.model.RemoteMovie
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

/**
 * Factory for creating test Movie and RemoteMovie instances.
 * Provides consistent test data across all test classes.
 */
object TestMovieFactory {

	fun createMovie(
		id: String = "1",
		title: String = "Test Movie",
		overview: String = "Test overview",
		voteAverage: Double = 7.5,
		posterPath: String = "/test_poster.jpg",
		backdropPath: String = "/test_backdrop.jpg",
	): Movie {
		return Movie(
			id = id,
			title = title,
			overview = overview,
			voteAverage = voteAverage,
			posterPath = posterPath,
			backdropPath = backdropPath
		)
	}

	fun createRemoteMovie(
		id: Int = 1,
		title: String = "Test Movie",
		originalTitle: String = title,
		overview: String = "Test overview",
		voteAverage: Double = 7.5,
		posterPath: String = "/test_poster.jpg",
		backdropPath: String = "/test_backdrop.jpg",
		releaseDate: String = "2024-01-01",
		popularity: Double = 100.0,
		voteCount: Int = 1000,
		genreIds: List<Int> = listOf(28, 12),
		adult: Boolean = false,
		video: Boolean = false,
		originalLanguage: String = "en",
	): RemoteMovie {
		return RemoteMovie(
			adult = adult,
			backdropPath = backdropPath,
			genreIds = genreIds,
			id = id,
			originalLanguage = originalLanguage,
			originalTitle = originalTitle,
			overview = overview,
			popularity = popularity,
			posterPath = posterPath,
			releaseDate = releaseDate,
			title = title,
			video = video,
			voteAverage = voteAverage,
			voteCount = voteCount
		)
	}

	@OptIn(ExperimentalTime::class)
	fun createMovieList(count: Int = 3, startId: Int = 1): List<Movie> {
		return (startId until startId + count).map { id ->
			createMovie(
				id = "${id}_${Clock.System.now()}",
				title = "Test Movie $id",
				overview = "Test overview for movie $id"
			)
		}
	}

	fun createRemoteMovieList(count: Int = 3, startId: Int = 1): List<RemoteMovie> {
		return (startId until startId + count).map { id ->
			createRemoteMovie(
				id = id,
				title = "Test Movie $id",
				overview = "Test overview for movie $id"
			)
		}
	}
}
