package com.movie.tmdb.feat.movie_list.presentation.ui.movie_list.state

import androidx.paging.PagingData
import com.movie.tmdb.feat.movie_list.domain.model.Movie
import kotlinx.coroutines.flow.Flow

data class MovieListState(
	val moviesFlow: Flow<PagingData<Movie>>? = null,
	val searchQuery: String? = null,
	val isSearching: Boolean = false,
	val searchError: String? = null,
)
