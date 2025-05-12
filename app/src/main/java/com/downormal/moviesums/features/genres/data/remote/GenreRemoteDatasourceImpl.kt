package com.downormal.moviesums.features.genres.data.remote

import com.downormal.moviesums.core.data.safeCall
import com.downormal.moviesums.core.domain.DataError
import com.downormal.moviesums.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
class GenreRemoteDatasourceImpl @Inject constructor(
    private val httpClient: HttpClient
) : GenreRemoteDatasource {
    override suspend fun getGenres(): Result<GenresDto, DataError.Remote> = safeCall<GenresDto> {
        httpClient.get("genre/movie/list") {
            parameter("language", "en")
        }
    }
}