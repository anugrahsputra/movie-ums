package com.downormal.moviesums.features.detail.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.downormal.moviesums.features.detail.data.dto.ReviewDto
import com.downormal.moviesums.core.domain.Result

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
class MovieReviewsPagingSource(
    private val dataSource: MovieDetailDatasource,
    private val movieId: Int
) : PagingSource<Int, ReviewDto>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewDto> {
        val page = params.key ?: 1
        return when (val result = dataSource.getMovieReviews(page, movieId)) {
            is Result.Success -> {
                val reviews = result.data.results
                LoadResult.Page(
                    data = reviews,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (reviews.isEmpty()) null else page + 1
                )

            }

            is Result.Error -> {
                LoadResult.Error(Exception("Remote error: ${result.error::class.simpleName}"))
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ReviewDto>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}