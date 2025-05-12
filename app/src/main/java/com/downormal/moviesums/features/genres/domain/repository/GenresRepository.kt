package com.downormal.moviesums.features.genres.domain.repository

import com.downormal.moviesums.core.domain.DataError
import com.downormal.moviesums.core.domain.Result
import com.downormal.moviesums.features.genres.domain.model.Genres

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
interface GenresRepository {
    suspend fun getGenres(): Result<Genres, DataError.Remote>
}