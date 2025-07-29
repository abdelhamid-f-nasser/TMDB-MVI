package com.movie.tmdb.feat.movie_list.domain.model

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MovieTest {

	@Nested
	inner class HasRating {
		@Test
		fun `when voteAverage is null should be false`() {
			val movie = Movie(
				id = "1",
				title = "Test Movie",
				overview = null,
				voteAverage = null,
				posterPath = null,
				backdropPath = null
			)
			assertThat(movie.hasRating).isFalse()
		}

		@Test
		fun `when voteAverage is zero should be false`() {
			val movie = Movie(
				id = "1",
				title = "Test Movie",
				overview = null,
				voteAverage = 0.0,
				posterPath = null,
				backdropPath = null
			)
			assertThat(movie.hasRating).isFalse()
		}

		@Test
		fun `when voteAverage is positive should be true`() {
			val movie = Movie(
				id = "1",
				title = "Test Movie",
				overview = null,
				voteAverage = 5.5,
				posterPath = null,
				backdropPath = null
			)
			assertThat(movie.hasRating).isTrue()
		}
	}

	@Nested
	inner class IsHighlyRated {
		@Test
		fun `when voteAverage is null should be false`() {
			val movie = Movie(
				id = "1",
				title = "Test Movie",
				overview = null,
				voteAverage = null,
				posterPath = null,
				backdropPath = null
			)
			assertThat(movie.isHighlyRated).isFalse()
		}

		@Test
		fun `when voteAverage is below 7 should be false`() {
			val movie = Movie(
				id = "1",
				title = "Test Movie",
				overview = null,
				voteAverage = 6.9,
				posterPath = null,
				backdropPath = null
			)
			assertThat(movie.isHighlyRated).isFalse()
		}

		@Test
		fun `when voteAverage is 7 or above should be true`() {
			val movie = Movie(
				id = "1",
				title = "Test Movie",
				overview = null,
				voteAverage = 7.0,
				posterPath = null,
				backdropPath = null
			)
			assertThat(movie.isHighlyRated).isTrue()
		}
	}
}
