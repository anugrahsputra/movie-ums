package com.downormal.moviesums.features.movies.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.downormal.moviesums.features.movies.data.mapper.toMovies
import com.downormal.moviesums.features.movies.data.remote.MovieListDataSource
import com.downormal.moviesums.features.movies.data.remote.MovieListPagingSource
import com.downormal.moviesums.features.movies.domain.model.Movies
import com.downormal.moviesums.features.movies.domain.repository.MovieListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
class MovieListRepositoryImpl @Inject constructor(
    private val dataSource: MovieListDataSource
) : MovieListRepository {
    override fun getMovieList(
        genres: String
    ): Flow<PagingData<Movies>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { MovieListPagingSource(dataSource, genres) }

        ).flow
            .map { pagingData ->
                pagingData.map { it.toMovies() }
            }
    }
}