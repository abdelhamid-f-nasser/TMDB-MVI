package com.movie.tmdb.foundation.presentation.ui

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Test

class EffectManagerTest {

	@Test
	fun `initial state should be null`() {
		// Arrange

		// Act
		val effectManager = EffectManager<TestEffect>()

		// Assert
		assertThat(effectManager.effect.value).isNull()
	}

	@Test
	fun `emit should set effect value`() {
		// Arrange
		val effectManager = EffectManager<TestEffect>()
		val testEffect = TestEffect.ShowMessage("test")

		// Act
		effectManager.emit(testEffect)

		//Assert
		assertThat(effectManager.effect.value).isEqualTo(testEffect)
	}

	@Test
	fun `consume should clear effect to null`() {
		// Arrange
		val effectManager = EffectManager<TestEffect>().apply {
			emit(TestEffect.ShowMessage("test"))
		}

		// Act
		effectManager.consume()

		//Assert
		assertThat(effectManager.effect.value).isNull()
	}

	@Test
	fun `effect flow should emit values in sequence`() = runTest {
		val effectManager = EffectManager<TestEffect>()

		effectManager.effect.test {
			assertThat(awaitItem()).isNull()

			effectManager.emit(TestEffect.ShowMessage("first"))
			assertThat(awaitItem()).isEqualTo(TestEffect.ShowMessage("first"))

			effectManager.emit(TestEffect.ShowError)
			assertThat(awaitItem()).isEqualTo(TestEffect.ShowError)

			effectManager.emit(TestEffect.NavigateBack)
			assertThat(awaitItem()).isEqualTo(TestEffect.NavigateBack)

			effectManager.consume()
			assertThat(awaitItem()).isNull()

			cancel()
		}
	}
}
