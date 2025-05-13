package com.downormal.moviesums.features.detail.domain.usecase

import com.downormal.moviesums.features.detail.domain.repository.MovieDetailRepository
import javax.inject.Inject

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
class GetUserReviewsUseCase @Inject constructor(
    private val repository: MovieDetailRepository
) {
     operator fun invoke(movieId: Int) = repository.getMovieReviews(movieId)
}