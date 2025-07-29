package com.movie.tmdb.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class NetworkConnectionInterceptor @Inject constructor(
	@param:ApplicationContext private val context: Context
) : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		if (!isCurrentlyConnected(context)) {
			throw NoConnectivityException()
		}
		val builder: Request.Builder = chain.request().newBuilder()
		return chain.proceed(builder.build())
	}
	private fun isCurrentlyConnected(context: Context): Boolean {
		val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val network = connectivityManager.activeNetwork ?: return false
		val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

		return when {
			activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
			activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
			activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
			else -> false
		}
	}
}

class NoConnectivityException : IOException() {
	override val message: String
		get() = "No Internet Connection"
}
