package com.movie.tmdb.foundation.presentation.ui.delegates

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DefaultEffectManagerTest {
    sealed class TestEffect { object ShowToast : TestEffect() }


	lateinit var sut: DefaultEffectManager<TestEffect>

	@BeforeEach
	fun setup() {
		sut = DefaultEffectManager()

	}

    @Test
    fun `initial effect is null by default`() = runTest {
        sut.effect.test {
            assertThat(awaitItem()).isNull()
            cancelAndIgnoreRemainingEvents()
        }
    }

	@Test
	fun `emitEffect sets the effect`() = runTest {
		sut.effect.test {
			assertThat(awaitItem()).isNull() // initial value
			sut.emitEffect(TestEffect.ShowToast)
			assertThat(awaitItem()).isEqualTo(TestEffect.ShowToast)
			cancelAndIgnoreRemainingEvents()
		}
	}

    @Test
    fun `consumeEffect clears the effect`() = runTest {
        sut.effect.test {
            assertThat(awaitItem()).isNull()
			sut.emitEffect(TestEffect.ShowToast)
			sut.consumeEffect()
            assertThat(awaitItem()).isEqualTo(TestEffect.ShowToast)
            assertThat(awaitItem()).isNull()
            cancelAndIgnoreRemainingEvents()
        }
    }
}
