package com.movie.tmdb.feat.movie_list.data.datasource.remote.model.mapper

import com.google.common.truth.Truth.assertThat
import com.movie.tmdb.feat.movie_list.data.datasource.remote.model.RemoteMovie
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class RemoteMovieMapperExtensionsTest {
	@BeforeEach
	fun setUp() {

	}

	@Test
	fun `Remote Movie toDomain mapper extensions should return valid Movie`() {
		val expected = Movie(
			id = "278",
			title = "The Shawshank Redemption",
			overview = "Imprisoned in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
			voteAverage = 8.712,
			posterPath = "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg",
			backdropPath = "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg"
		)

		val remoteMovie = RemoteMovie(
			adult = false,
			backdropPath = "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg",
			genreIds = listOf(18, 80),
			id = 278,
			originalLanguage = "en",
			originalTitle = "The Shawshank Redemption",
			overview = "Imprisoned in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
			popularity = 36.1628,
			posterPath = "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg",
			releaseDate = "1994-09-23",
			title = "The Shawshank Redemption",
			video = false,
			voteAverage = 8.712,
			voteCount = 28581
		)

		// Act
		val actual = remoteMovie.toDomain()

		assertThat(actual).isEqualTo(expected)
	}

}
