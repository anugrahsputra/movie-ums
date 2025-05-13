package com.downormal.moviesums.features.detail.presentation

import com.downormal.moviesums.features.detail.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movieDetail: MovieDetail? = null,
    val error: String? = null
)