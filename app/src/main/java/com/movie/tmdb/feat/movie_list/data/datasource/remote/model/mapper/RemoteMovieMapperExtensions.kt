package com.movie.tmdb.feat.movie_list.data.datasource.remote.model.mapper

import com.movie.tmdb.feat.movie_list.data.datasource.local.model.LocalMovie
import com.movie.tmdb.feat.movie_list.data.datasource.remote.model.RemoteMovie
import com.movie.tmdb.feat.movie_list.domain.model.Movie

/**
 * Extension function to convert RemoteMovie to Movie domain model.
 */
fun RemoteMovie.toDomain(): Movie = Movie(
	id = id.toString(),
	title = title,
	overview = overview,
	voteAverage = voteAverage,
	posterPath = posterPath,
	backdropPath = backdropPath
)


/**
 * Extension function to convert [RemoteMovie] to the database's [LocalMovie]
 */
fun RemoteMovie.toLocal(): LocalMovie = LocalMovie(
	id = 0,
	remoteId = id.toString(),
	adult = adult,
	backdropPath = backdropPath,
	genreIds = genreIds,
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
