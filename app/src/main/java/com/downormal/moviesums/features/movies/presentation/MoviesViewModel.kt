package com.downormal.moviesums.features.movies.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.downormal.moviesums.app.Route
import com.downormal.moviesums.features.movies.domain.model.Movies
import com.downormal.moviesums.features.movies.domain.usecase.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    getMovieList: GetMovieListUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val genres = savedStateHandle.toRoute<Route.Movies>().genres

    val movies: Flow<PagingData<Movies>> = getMovieList(genres).cachedIn(viewModelScope)

}