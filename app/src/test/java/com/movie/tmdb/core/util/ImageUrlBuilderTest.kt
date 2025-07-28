package com.movie.tmdb.core.util

import com.google.common.truth.Truth.assertThat
import com.movie.tmdb.core.constants.ApiConstants
import org.junit.jupiter.api.Test

class ImageUrlBuilderTest {

    @Test
    fun `buildPosterUrl should return correct URL for valid poster path`() {
        // Arrange
        val posterPath = "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg"
        val expectedUrl = "${ApiConstants.TMDB_IMAGE_BASE_URL}${ApiConstants.ImageSizes.POSTER_W500}$posterPath"

        // Act
        val result = ImageUrlBuilder.buildPosterUrl(posterPath)

        // Assert
        assertThat(result).isEqualTo(expectedUrl)
    }

    @Test
    fun `buildPosterUrl should return null for null poster path`() {
        // Act
        val result = ImageUrlBuilder.buildPosterUrl(null)

        // Assert
        assertThat(result).isNull()
    }

    @Test
    fun `buildPosterUrl should use custom size when provided`() {
        // Arrange
        val posterPath = "/test_poster.jpg"
        val customSize = ApiConstants.ImageSizes.POSTER_W185
        val expectedUrl = "${ApiConstants.TMDB_IMAGE_BASE_URL}$customSize$posterPath"

        // Act
        val result = ImageUrlBuilder.buildPosterUrl(posterPath, customSize)

        // Assert
        assertThat(result).isEqualTo(expectedUrl)
    }

    @Test
    fun `buildBackdropUrl should return correct URL for valid backdrop path`() {
        // Arrange
        val backdropPath = "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg"
        val expectedUrl = "${ApiConstants.TMDB_IMAGE_BASE_URL}${ApiConstants.ImageSizes.BACKDROP_W780}$backdropPath"

        // Act
        val result = ImageUrlBuilder.buildBackdropUrl(backdropPath)

        // Assert
        assertThat(result).isEqualTo(expectedUrl)
    }

    @Test
    fun `buildBackdropUrl should return null for null backdrop path`() {
        // Act
        val result = ImageUrlBuilder.buildBackdropUrl(null)

        // Assert
        assertThat(result).isNull()
    }

    @Test
    fun `buildBackdropUrl should use custom size when provided`() {
        // Arrange
        val backdropPath = "/test_backdrop.jpg"
        val customSize = ApiConstants.ImageSizes.BACKDROP_W1280
        val expectedUrl = "${ApiConstants.TMDB_IMAGE_BASE_URL}$customSize$backdropPath"

        // Act
        val result = ImageUrlBuilder.buildBackdropUrl(backdropPath, customSize)

        // Assert
        assertThat(result).isEqualTo(expectedUrl)
    }
} 