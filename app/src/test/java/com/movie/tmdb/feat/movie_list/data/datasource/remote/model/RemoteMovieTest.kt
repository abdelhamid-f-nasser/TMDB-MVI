package com.movie.tmdb.feat.movie_list.data.datasource.remote.model

import com.google.common.truth.Truth.assertThat
import com.movie.tmdb.core.testutil.moshiAdapter
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class RemoteMovieTest {
	@Nested
	@DisplayName("Deserialize")
	inner class JsonDeserializationTest {
		private val sampleMovieJson = """
            {
              "adult": false,
              "backdrop_path": "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg",
              "genre_ids": [18, 80],
              "id": 278,
              "original_language": "en",
              "original_title": "The Shawshank Redemption",
              "overview": "Imprisoned in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
              "popularity": 36.1628,
              "poster_path": "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg",
              "release_date": "1994-09-23",
              "title": "The Shawshank Redemption",
              "video": false,
              "vote_average": 8.712,
              "vote_count": 28581
            }
        """

		private val expected = RemoteMovie(
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

		@Test
		fun `movie with valid json should return valid RemoteMovie`() {
			// Arrange
			val adapter = moshiAdapter<RemoteMovie>()

			// Act
			val movie = adapter.fromJson(sampleMovieJson)

			// Assert
			assertThat(movie).isEqualTo(expected)
		}
	}
}
