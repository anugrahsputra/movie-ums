package com.downormal.moviesums.features.genres.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */

@Serializable
data class GenresDto(
    @SerialName("genres") val genres: List<GenreDto>
)

@Serializable
data class GenreDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,

    )