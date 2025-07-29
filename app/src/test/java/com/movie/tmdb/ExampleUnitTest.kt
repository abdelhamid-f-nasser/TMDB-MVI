package com.movie.tmdb


import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalTime::class)
class ExampleUnitTest {

	@BeforeEach
	fun setup() {
		MockKAnnotations.init(this)
	}


	@MockK
	lateinit var time: Instant

	@Test
	fun addition_isCorrect() {
		assertThat(true).isTrue()
		assertThat(1 + 2).isEqualTo(3)
		assertThat(time).isNotNull()
	}
}
