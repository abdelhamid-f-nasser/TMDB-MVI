package com.movie.tmdb.feat.movie_list.data.datasource.remote.model.mapper

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
