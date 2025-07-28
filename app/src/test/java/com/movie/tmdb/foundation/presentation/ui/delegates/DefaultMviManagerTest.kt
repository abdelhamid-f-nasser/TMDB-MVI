package com.movie.tmdb.foundation.presentation.ui.delegates

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class DefaultMviManagerTest {
    data class TestState(val count: Int = 0)
    sealed class TestEffect

	@Test
    fun `updateState should update state in order`() = runTest {
        val mvi = DefaultMviManager<TestState, String, TestEffect>(
            initialState = TestState(0),
            onIntent = {},
            initialEffect = null
        )
        mvi.uiState.test {
            assertThat(awaitItem().count).isEqualTo(0) // initial
            mvi.updateState { copy(count = 1) }
            assertThat(awaitItem().count).isEqualTo(1)
            mvi.updateState { copy(count = 2) }
            assertThat(awaitItem().count).isEqualTo(2)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `emitEffect should emit effects in order`() = runTest {
        val mvi = DefaultMviManager<TestState, String, String>(
            initialState = TestState(),
            onIntent = {},
            initialEffect = null
        )
        mvi.effect.test {
            assertThat(awaitItem()).isNull() // initial
            mvi.emitEffect("A")
            assertThat(awaitItem()).isEqualTo("A")
            mvi.emitEffect("B")
            assertThat(awaitItem()).isEqualTo("B")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `DefaultMviManager when initialEffect is set should emit it immediately`() = runTest {
        val mvi = DefaultMviManager<TestState, String, String>(
            initialState = TestState(),
            onIntent = {},
            initialEffect = "Welcome"
        )
        mvi.effect.test {
            assertThat(awaitItem()).isEqualTo("Welcome")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `handleIntent should delegate to provided lambda`() {
        var actual: String? = null
        val mvi = DefaultMviManager<TestState, String, TestEffect>(
            initialState = TestState(),
            onIntent = { actual = it },
            initialEffect = null
        )
        mvi.handleIntent("TestIntent")
        assertThat(actual).isEqualTo("TestIntent")
    }
}
