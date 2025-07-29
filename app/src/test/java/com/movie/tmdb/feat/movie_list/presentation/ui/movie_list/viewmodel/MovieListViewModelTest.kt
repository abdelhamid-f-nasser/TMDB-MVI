package com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.viewmodel

import androidx.paging.PagingData
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import com.movie.tmdb.feat.movie_list.domain.usecase.GetMoviesUseCase
import com.movie.tmdb.feat.movie_list.domain.usecase.SearchMoviesUseCase
import com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.effect.MovieListEffect
import com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.intent.MovieListIntent
import com.movie.tmdb.testutil.TestMovieFactory
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieListViewModelTest {

	private lateinit var getMoviesUseCase: GetMoviesUseCase
	private lateinit var searchMoviesUseCase: SearchMoviesUseCase
	private lateinit var loadMoviesData: PagingData<Movie>
	private lateinit var searchMoviesData: PagingData<Movie>

	private lateinit var sut: MovieListViewModel

	@BeforeEach
	fun setup() {
		getMoviesUseCase = mockk()
		searchMoviesUseCase = mockk()

		loadMoviesData = PagingData.from(TestMovieFactory.createMovieList(1, 10))
		searchMoviesData = PagingData.from(TestMovieFactory.createMovieList(1, 10))

		every { getMoviesUseCase() } returns flowOf(loadMoviesData)
		every { searchMoviesUseCase(any()) } returns flowOf(searchMoviesData)
		sut = MovieListViewModel(getMoviesUseCase, searchMoviesUseCase)
	}

	@Test
	fun `initial state should have moviesFlow populated`() = runTest {
		advanceUntilIdle()
		sut.uiState.test {
			val state = awaitItem()
			assertThat(state.moviesFlow).isNotNull()
			assertThat(state.searchQuery).isNull()
			assertThat(state.isSearching).isFalse()
			assertThat(state.searchError).isNull()
		}
	}

	@Test
	fun `handleIntent with MovieClicked should emit navigation effect`() = runTest {
		val clickedMovie = TestMovieFactory.createMovie()

		sut.effect.test {
			assertThat(awaitItem()).isNull()

			sut.handleIntent(MovieListIntent.MovieClicked(clickedMovie))

			val effect = awaitItem()
			assertThat(effect).isInstanceOf(MovieListEffect.NavigateToMovieDetails::class.java)
			assertThat((effect as MovieListEffect.NavigateToMovieDetails).movie).isEqualTo(clickedMovie)
		}
	}

	@Test
	fun `handleIntent with RefreshMovies should keep moviesFlow populated`() = runTest {
		advanceUntilIdle()
		sut.handleIntent(MovieListIntent.RefreshMovies)
		advanceUntilIdle()
		sut.uiState.test {
			val state = awaitItem()
			assertThat(state.moviesFlow).isNotNull()
		}
	}

	@Test
	fun `handleIntent with SearchMovies should update searchQuery and moviesFlow`() = runTest {
		val query = "Batman"
		sut.handleIntent(MovieListIntent.SearchMovies(query))
		advanceUntilIdle()
		sut.uiState.test {
			val state = awaitItem()
			assertThat(state.searchQuery).isEqualTo(query)
			assertThat(state.moviesFlow).isNotNull()
		}
	}

}
