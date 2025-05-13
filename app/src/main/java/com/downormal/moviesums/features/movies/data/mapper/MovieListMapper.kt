package com.downormal.moviesums.features.movies.data.mapper

import com.downormal.moviesums.features.movies.data.dto.MovieListDto
import com.downormal.moviesums.features.movies.data.dto.MoviesDto
import com.downormal.moviesums.features.movies.domain.model.MovieList
import com.downormal.moviesums.features.movies.domain.model.Movies

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */

fun MovieListDto.toMovieList(): MovieList {
    return MovieList(
        page = page,
        results = results.map { it.toMovies() }
    )
}
fun MoviesDto.toMovies(): Movies {
    return Movies(
        adult = adult == true,
        backdropPath = backdropPath ?: "",
        genreIds = genreIds ?: emptyList(),
        id = id ?: 0,
        originalLanguage = originalLanguage ?: "",
        originalTitle = originalTitle ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: "",
        title = title ?: "",
        video = video == true,
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0
    )
}