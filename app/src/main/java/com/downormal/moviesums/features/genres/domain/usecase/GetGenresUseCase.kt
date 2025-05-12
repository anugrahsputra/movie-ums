package com.downormal.moviesums.features.genres.domain.usecase

import com.downormal.moviesums.features.genres.domain.repository.GenresRepository
import javax.inject.Inject

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
class GetGenresUseCase @Inject constructor(
    private val repository: GenresRepository
) {
    suspend operator fun invoke() = repository.getGenres()
}