package com.downormal.moviesums.features.genres.presentation

import com.downormal.moviesums.features.genres.domain.model.Genre

data class GenresState(
    val isLoading: Boolean = false,
    val genre: List<Genre> = emptyList(),
    val error: String? = null
)