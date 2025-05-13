package com.downormal.moviesums.features.detail.domain.usecase

import com.downormal.moviesums.features.detail.domain.repository.MovieDetailRepository
import javax.inject.Inject

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieDetailRepository
){
    suspend operator fun invoke(movieId: Int) = repository.getMovieDetail(movieId)
}