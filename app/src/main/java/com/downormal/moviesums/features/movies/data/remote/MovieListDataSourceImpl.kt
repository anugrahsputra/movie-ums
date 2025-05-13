package com.downormal.moviesums.features.movies.data.remote

import com.downormal.moviesums.core.data.safeCall
import com.downormal.moviesums.core.domain.DataError
import com.downormal.moviesums.core.domain.Result
import com.downormal.moviesums.features.movies.data.dto.MovieListDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
class MovieListDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient
) : MovieListDataSource {
    override suspend fun getMovieList(page: Int, genres: String): Result<MovieListDto, DataError.Remote> =
        safeCall<MovieListDto> {
            httpClient.get(
                "discover/movie"
            ) {
                parameter("page", page)
                parameter("include_adult", false)
                parameter("include_video", true)
                parameter("language", "en-US")
                parameter("sort_by", "popularity.desc")
                parameter("with_genres", genres)

            }
        }
}