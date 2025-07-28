package com.movie.tmdb.core.util

import android.os.Build
import android.os.Bundle
import android.os.Parcelable


inline fun <reified T: Parcelable> Bundle.getParcelableCompat(id: String) : T? {
	return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
		getParcelable(id, T::class.java)
	} else {
		@Suppress("DEPRECATION")
		getParcelable<T>(id)
	}
}
