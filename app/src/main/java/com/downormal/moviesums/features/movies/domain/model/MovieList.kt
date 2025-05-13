package com.downormal.moviesums.features.movies.domain.model

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
data class MovieList(
    val page: Int,
    val results: List<Movies>,
)

data class Movies(
    var adult: Boolean,
    var backdropPath: String,
    var genreIds: List<Int>,
    var id: Int,
    var originalLanguage: String,
    var originalTitle: String,
    var overview: String,
    var popularity: Double,
    var posterPath: String,
    var releaseDate: String,
    var title: String,
    var video: Boolean,
    var voteAverage: Double,
    var voteCount: Int
)