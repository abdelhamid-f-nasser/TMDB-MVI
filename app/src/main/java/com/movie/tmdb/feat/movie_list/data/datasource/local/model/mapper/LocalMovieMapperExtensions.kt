package com.movie.tmdb.feat.movie_list.data.datasource.local.model.mapper

import com.movie.tmdb.feat.movie_list.data.datasource.local.model.LocalMovie
import com.movie.tmdb.feat.movie_list.domain.model.Movie

/**
 * Extension function to convert Local Database's [LocalMovie] to the Domain's [Movie]
 */
fun LocalMovie.toDomain(): Movie = Movie(
	id = id.toString(),
	title = title,
	overview = overview,
	voteAverage = voteAverage,
	posterPath = posterPath,
	backdropPath = backdropPath
)
