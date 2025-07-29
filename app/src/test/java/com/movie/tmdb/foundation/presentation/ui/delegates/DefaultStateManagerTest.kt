package com.movie.tmdb.foundation.presentation.ui.delegates

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class DefaultStateManagerTest {
	data class TestState(val count: Int = 0)

	@Test
	fun `initial state is set correctly`() = runTest {
		val sut = DefaultStateManager(TestState(5))
		sut.uiState.test {
			assertThat(awaitItem().count).isEqualTo(5)
			cancelAndIgnoreRemainingEvents()
		}
		assertThat(sut.getCurrentState().count).isEqualTo(5)
	}

	@Test
	fun `updateState updates the state immutably`() = runTest {
		val sut = DefaultStateManager(TestState(1))
		sut.updateState { copy(count = count + 1) }
		sut.uiState.test {
			assertThat(awaitItem().count).isEqualTo(2)
			cancelAndIgnoreRemainingEvents()
		}
		assertThat(sut.getCurrentState().count).isEqualTo(2)
	}


	@Test
	fun `getCurrentState should return the currentState`() = runTest {
		val sut = DefaultStateManager(TestState(1))
		sut.updateState { copy(count = count + 1) }
		val actual = sut.getCurrentState()
		assertThat(actual.count).isEqualTo(2)
	}
}
