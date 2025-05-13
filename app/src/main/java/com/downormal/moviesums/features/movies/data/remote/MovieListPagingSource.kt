package com.downormal.moviesums.features.movies.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.downormal.moviesums.core.domain.Result
import com.downormal.moviesums.features.movies.data.dto.MoviesDto

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
class MovieListPagingSource(
    private val dataSource: MovieListDataSource,
    private val genres: String
) : PagingSource<Int, MoviesDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesDto> {
        val page = params.key ?: 1
        return when (val result = dataSource.getMovieList(page, genres)) {
            is Result.Success -> {
                val movies = result.data.results

                LoadResult.Page(
                    data = movies,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (movies.isEmpty()) null else page + 1
                )
            }

            is Result.Error -> {
                LoadResult.Error(Exception("Remote error: ${result.error::class.simpleName}"))
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MoviesDto>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}