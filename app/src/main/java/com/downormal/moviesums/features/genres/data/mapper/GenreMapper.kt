package com.downormal.moviesums.features.genres.data.mapper

import com.downormal.moviesums.features.genres.data.remote.GenreDto
import com.downormal.moviesums.features.genres.data.remote.GenresDto
import com.downormal.moviesums.features.genres.domain.model.Genre
import com.downormal.moviesums.features.genres.domain.model.Genres

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */

fun GenresDto.toGenre(): Genres {
    return Genres(
        genres = genres.map { it.toGenre() }
    )
}

fun GenreDto.toGenre(): Genre {
    return Genre(
        id = id,
        name = name
    )
}