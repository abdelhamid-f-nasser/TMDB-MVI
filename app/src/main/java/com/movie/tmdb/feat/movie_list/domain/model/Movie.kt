package com.movie.tmdb.feat.movie_list.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Movie Domain model.
 */
@Parcelize
data class Movie(
    val id: String,
    val title: String,
    val overview: String?,
    val voteAverage: Double?,
    val posterPath: String?,
    val backdropPath: String?,
) : Parcelable {
	val hasRating: Boolean
		get() = voteAverage != null && voteAverage > 0

	val isHighlyRated: Boolean
		get() = voteAverage?.let { it >= 7.0 } ?: false
}
