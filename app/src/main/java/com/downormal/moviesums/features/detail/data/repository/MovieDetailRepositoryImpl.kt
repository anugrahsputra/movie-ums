package com.downormal.moviesums.features.detail.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.downormal.moviesums.core.domain.DataError
import com.downormal.moviesums.core.domain.Result
import com.downormal.moviesums.core.domain.map
import com.downormal.moviesums.features.detail.data.mapper.toMovieDetail
import com.downormal.moviesums.features.detail.data.mapper.toReview
import com.downormal.moviesums.features.detail.data.remote.MovieDetailDatasource
import com.downormal.moviesums.features.detail.data.remote.MovieReviewsPagingSource
import com.downormal.moviesums.features.detail.domain.model.MovieDetail
import com.downormal.moviesums.features.detail.domain.model.Review
import com.downormal.moviesums.features.detail.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
class MovieDetailRepositoryImpl @Inject constructor(
    private val datasource: MovieDetailDatasource
) : MovieDetailRepository {
    override suspend fun getMovieDetail(movieId: Int): Result<MovieDetail, DataError.Remote> {
        return datasource
            .getMovieDetail(movieId)
            .map { it.toMovieDetail() }
    }

    override fun getMovieReviews(movieId: Int): Flow<PagingData<Review>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { MovieReviewsPagingSource(datasource, movieId) }
        ).flow
            .map { pagingData ->
                pagingData.map { it.toReview() }
            }

    }

}