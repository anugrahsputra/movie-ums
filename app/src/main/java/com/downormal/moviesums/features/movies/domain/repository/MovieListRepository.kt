package com.downormal.moviesums.features.movies.domain.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.downormal.moviesums.features.movies.domain.model.Movies
import kotlinx.coroutines.flow.Flow

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
interface MovieListRepository {
     fun getMovieList(genres: String): Flow<PagingData<Movies>>

}