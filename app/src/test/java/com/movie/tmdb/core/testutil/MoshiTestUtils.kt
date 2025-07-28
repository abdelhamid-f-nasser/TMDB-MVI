package com.movie.tmdb.core.testutil

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * Returns a Moshi JsonAdapter for a non-generic type.
 */
inline fun <reified T> moshiAdapter(): JsonAdapter<T> =
    Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(T::class.java)

/**
 * Returns a Moshi JsonAdapter for a generic type, e.g., PaginatedResponse<RemoteMovie>.
 * Usage: moshiGenericAdapter<PaginatedResponse<RemoteMovie>, RemoteMovie>()
 */
inline fun <reified T, reified R> moshiGenericAdapter(): JsonAdapter<T> {
    val type = Types.newParameterizedType(T::class.java, R::class.java)
    return Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(type)
} 