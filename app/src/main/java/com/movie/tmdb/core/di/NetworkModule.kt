package com.movie.tmdb.core.di

import com.movie.tmdb.core.constants.ApiConstants
import com.movie.tmdb.core.secrets.SecretsProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Hilt module that provides network-related dependencies.
 *
 * Provides singleton instances of networking components like OkHttp, Retrofit, and Moshi
 * configured for TMDB API integration.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Provides
	@Singleton
	fun provideMoshi(): Moshi {
		return Moshi.Builder()
			.add(KotlinJsonAdapterFactory())
			.build()
	}

	@Provides
	@Singleton
	fun provideLoggingInterceptor(): HttpLoggingInterceptor {
		return HttpLoggingInterceptor().apply {
			level = HttpLoggingInterceptor.Level.BODY
		}
	}

	@Provides
	@Singleton
	fun provideApiKeyInterceptor(secretsProvider: SecretsProvider): Interceptor {
		return Interceptor { chain ->
			val url = chain.request().url.newBuilder()
				.addQueryParameter(ApiConstants.QueryParamsKeys.API_KEY, secretsProvider.getTmdbApiKey())
				.build()
			val request = chain.request().newBuilder().url(url).build()
			chain.proceed(request)
		}
	}

	@Provides
	@Singleton
	fun provideOkHttpClient(
		apiKeyInterceptor: Interceptor,
		loggingInterceptor: HttpLoggingInterceptor,
	): OkHttpClient {
		return OkHttpClient.Builder()
			.addInterceptor(apiKeyInterceptor)
			.addInterceptor(loggingInterceptor)
			.connectTimeout(30, TimeUnit.SECONDS)
			.readTimeout(30, TimeUnit.SECONDS)
			.writeTimeout(30, TimeUnit.SECONDS)
			.build()
	}

	@Provides
	@Singleton
	fun provideRetrofit(
		okHttpClient: OkHttpClient,
		moshi: Moshi
	): Retrofit {
		return Retrofit.Builder()
			.baseUrl(ApiConstants.TMDB_BASE_URL_V3)
			.client(okHttpClient)
			.addConverterFactory(MoshiConverterFactory.create(moshi))
			.build()
	}
}
