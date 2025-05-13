package com.downormal.moviesums.features.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.downormal.moviesums.app.Route
import com.downormal.moviesums.core.domain.onError
import com.downormal.moviesums.core.domain.onSuccess
import com.downormal.moviesums.features.detail.domain.model.Review
import com.downormal.moviesums.features.detail.domain.usecase.GetMovieDetailUseCase
import com.downormal.moviesums.features.detail.domain.usecase.GetUserReviewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetail: GetMovieDetailUseCase,
    private val getUserReviews: GetUserReviewsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private var hasLoadedInitialData = false
    private val movieId = savedStateHandle.toRoute<Route.MovieDetail>().id

    private val _state = MutableStateFlow(MovieDetailState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                loadMovieDetail()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = MovieDetailState()
        )

    fun onEvent(event: MovieDetailEvent) {
        when (event) {
            else -> TODO("Handle actions")
        }
    }

    private fun loadMovieDetail() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }

        getMovieDetail(movieId)
            .onSuccess { success ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        movieDetail = success
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = error.toString()
                    )
                }
            }

    }

    val userReviews: Flow<PagingData<Review>> = getUserReviews(movieId)
        .cachedIn(viewModelScope)
}