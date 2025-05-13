package com.downormal.moviesums.features.movies.presentation

import com.downormal.moviesums.features.movies.domain.model.Movies

data class MoviesState(
    val movies: List<Movies> = emptyList()
)