package com.downormal.moviesums.features.genres.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downormal.moviesums.app.Route
import com.downormal.moviesums.core.domain.onError
import com.downormal.moviesums.core.domain.onSuccess
import com.downormal.moviesums.features.genres.domain.usecase.GetGenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val getGenres: GetGenresUseCase
) : ViewModel() {

    private var hasLoadedInitialData = false
    private val _navEvent = Channel<String>()
    val navEvent = _navEvent.receiveAsFlow()

    private val _state = MutableStateFlow(GenresState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                loadGenres()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = GenresState()
        )

    fun onEvent(event: GenresEvent) {
        when (event) {
            is GenresEvent.OnSelectedGenres -> {
                val currentGenreId = _state.value.selectedGenreIds.toMutableSet()
                if (currentGenreId.contains(event.genreId)) {
                    currentGenreId.remove(event.genreId)
                } else {
                    currentGenreId.add(event.genreId)
                }

                val currentGenreName = _state.value.selectedGenreName.toMutableSet()
                if (currentGenreName.contains(event.genreName)) {
                    currentGenreName.remove(event.genreName)
                } else {
                    currentGenreName.add(event.genreName)
                }

                _state.update { it.copy(selectedGenreIds = currentGenreId, selectedGenreName = currentGenreName) }
            }

            GenresEvent.OnSubmit -> {

            }
        }
    }

    private fun loadGenres() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }

        getGenres()
            .onSuccess { success ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        genre = success.genres
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
}