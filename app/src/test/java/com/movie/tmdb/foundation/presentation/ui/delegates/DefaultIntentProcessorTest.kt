package com.movie.tmdb.foundation.presentation.ui.delegates

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class DefaultIntentProcessorTest {
    @Test
    fun `handleIntent calls the provided lambda`() {
        var actual: String? = null
        val processor = DefaultIntentProcessor<String> { intent ->
            actual = intent
        }
        processor.handleIntent("TestIntent")
        assertThat(actual).isEqualTo("TestIntent")
    }
}
