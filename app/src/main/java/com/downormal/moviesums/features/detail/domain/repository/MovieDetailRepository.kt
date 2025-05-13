package com.downormal.moviesums.features.detail.domain.repository

import androidx.paging.PagingData
import com.downormal.moviesums.core.domain.DataError
import com.downormal.moviesums.features.detail.domain.model.MovieDetail
import com.downormal.moviesums.core.domain.Result
import com.downormal.moviesums.features.detail.domain.model.Review
import kotlinx.coroutines.flow.Flow

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int): Result<MovieDetail, DataError.Remote>
    fun getMovieReviews(movieId: Int) : Flow<PagingData<Review>>
}