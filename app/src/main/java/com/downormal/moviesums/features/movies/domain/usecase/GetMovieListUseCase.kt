package com.downormal.moviesums.features.movies.domain.usecase

import androidx.paging.PagingData
import com.downormal.moviesums.features.movies.domain.model.Movies
import com.downormal.moviesums.features.movies.domain.repository.MovieListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
class GetMovieListUseCase  @Inject constructor(
    private val repository: MovieListRepository
){
    operator fun invoke(genres: String): Flow<PagingData<Movies>> {
        return repository.getMovieList(genres)
    }
}