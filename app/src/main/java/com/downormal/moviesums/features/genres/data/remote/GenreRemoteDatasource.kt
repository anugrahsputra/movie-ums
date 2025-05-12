package com.downormal.moviesums.features.genres.data.remote

import com.downormal.moviesums.core.domain.DataError
import com.downormal.moviesums.core.domain.Result

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
interface GenreRemoteDatasource {
    suspend fun getGenres() : Result<GenresDto, DataError.Remote>
}