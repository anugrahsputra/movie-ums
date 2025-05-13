package com.downormal.moviesums.features.detail.data.remote

import com.downormal.moviesums.core.data.safeCall
import com.downormal.moviesums.core.domain.DataError
import com.downormal.moviesums.core.domain.Result
import com.downormal.moviesums.features.detail.data.dto.MovieDetailDto
import com.downormal.moviesums.features.detail.data.dto.MovieReviewsDto
import com.downormal.moviesums.features.detail.domain.model.MovieReviews
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
class MovieDetailDatasourceImpl @Inject constructor(
    private val httpClient: HttpClient
) : MovieDetailDatasource{
    override suspend fun getMovieDetail(movieId: Int): Result<MovieDetailDto, DataError.Remote> = safeCall<MovieDetailDto> {
       httpClient.get(
           "movie/$movieId"
       ) {
           parameter("append_to_response", "videos")
           parameter("language", "en-US")
       }
    }

    override suspend fun getMovieReviews(page: Int, movieId: Int): Result<MovieReviewsDto, DataError.Remote> = safeCall<MovieReviewsDto> {
        httpClient.get(
           "movie/$movieId/reviews"
        ) {
            parameter("language", "en-US")
            parameter("page", page)

        }
    }
}