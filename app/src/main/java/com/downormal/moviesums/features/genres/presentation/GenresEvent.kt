package com.downormal.moviesums.features.genres.presentation

sealed interface GenresEvent {
    data class OnSelectedGenres(val genreId: Int, val genreName: String) : GenresEvent
    data object OnSubmit : GenresEvent
}