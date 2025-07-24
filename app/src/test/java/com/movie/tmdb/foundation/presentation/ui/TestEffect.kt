 package com.movie.tmdb.foundation.presentation.ui



sealed class TestEffect : UiEffect {
	object ShowError : TestEffect()
	data class ShowMessage(val message: String) : TestEffect()
	object NavigateBack : TestEffect()
}
