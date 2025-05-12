package com.downormal.moviesums.features.genres.domain.model

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
data class Genres(
    val genres: List<Genre>
)

data class Genre(
    val id: Int,
    val name: String,
)