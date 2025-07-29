package com.movie.tmdb.feat.movie_list.data.datasource.local.model.converter

import androidx.room.TypeConverter

class LocalMovieConvertors {

	@TypeConverter
	fun fromIntList(value: List<Int>): String {
		return value.joinToString(",")
	}

	@TypeConverter
	fun toIntList(value: String): List<Int> {
		return if (value.isEmpty()) emptyList() else value.split(",").map { it.toInt() }
	}
}
