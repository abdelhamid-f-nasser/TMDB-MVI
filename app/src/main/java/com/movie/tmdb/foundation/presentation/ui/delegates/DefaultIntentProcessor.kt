package com.movie.tmdb.foundation.presentation.ui.delegates

import com.movie.tmdb.foundation.presentation.ui.mvi.IntentProcessor

/**
 * Default implementation of [IntentProcessor] for intent delegation in MVI.
 *
 * Intended for use as a delegate in MVI architectures, providing intent handling via Kotlin delegation.
 *
 * @param Intent The type representing user or system intents/actions.
 * @property onIntent Lambda to handle incoming intents.
 */
class DefaultIntentProcessor<Intent>(
    private val onIntent: (Intent) -> Unit
) : IntentProcessor<Intent> {
    override fun handleIntent(intent: Intent) {
        onIntent(intent)
    }
} 