package com.downormal.moviesums.features.movies.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
@Serializable
data class MovieListDto(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<MoviesDto>,
)

@Serializable
data class MoviesDto(
    @SerialName("adult") var adult: Boolean?,
    @SerialName("backdrop_path") var backdropPath: String?,
    @SerialName("genre_ids") var genreIds: List<Int>?,
    @SerialName("id") var id: Int?,
    @SerialName("original_language") var originalLanguage: String?,
    @SerialName("original_title") var originalTitle: String?,
    @SerialName("overview") var overview: String?,
    @SerialName("popularity") var popularity: Double?,
    @SerialName("poster_path") var posterPath: String?,
    @SerialName("release_date") var releaseDate: String?,
    @SerialName("title") var title: String?,
    @SerialName("video") var video: Boolean?,
    @SerialName("vote_average") var voteAverage: Double?,
    @SerialName("vote_count") var voteCount: Int?
)