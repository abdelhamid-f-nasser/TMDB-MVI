package com.movie.tmdb.foundation.presentation.ui.mvi

/**
 * Contract for intent handling delegation in MVI architecture.
 * Provides the single entry point for all user actions and system events.
 *
 * @param Intent The type representing user or system intents/actions.
 *
 * Usage:
 * Implement this interface to provide intent handling logic for your ViewModel or delegate.
 */
interface IntentProcessor<Intent> {
    /**
     * Handles a single intent (user action or system event).
     *
     * @param intent The intent to handle.
     */
	fun handleIntent(intent: Intent)
}
