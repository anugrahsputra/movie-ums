package com.downormal.moviesums.features.detail.data.remote

import com.downormal.moviesums.core.domain.DataError
import com.downormal.moviesums.core.domain.Result
import com.downormal.moviesums.features.detail.data.dto.MovieDetailDto
import com.downormal.moviesums.features.detail.data.dto.MovieReviewsDto

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
interface MovieDetailDatasource {
    suspend fun getMovieDetail(movieId: Int): Result<MovieDetailDto, DataError.Remote>
    suspend fun getMovieReviews(page: Int, movieId: Int): Result<MovieReviewsDto, DataError.Remote>
}