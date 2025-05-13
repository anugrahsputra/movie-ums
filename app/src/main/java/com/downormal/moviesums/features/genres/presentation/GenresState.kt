package com.downormal.moviesums.features.genres.presentation

import com.downormal.moviesums.features.genres.domain.model.Genre

data class GenresState(
    val isLoading: Boolean = false,
    val genre: List<Genre> = emptyList(),
    val selectedGenreIds: Set<Int> = emptySet(),
    val selectedGenreName: Set<String> = emptySet(),
    val error: String? = null
)