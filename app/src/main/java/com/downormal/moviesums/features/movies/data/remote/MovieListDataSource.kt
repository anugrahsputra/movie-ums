package com.downormal.moviesums.features.movies.data.remote

import com.downormal.moviesums.core.domain.DataError
import com.downormal.moviesums.core.domain.Result
import com.downormal.moviesums.features.movies.data.dto.MovieListDto

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
interface MovieListDataSource {
    suspend fun getMovieList(page: Int, genres: String): Result<MovieListDto, DataError.Remote>
}