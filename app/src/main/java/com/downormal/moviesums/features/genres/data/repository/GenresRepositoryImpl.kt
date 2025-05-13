package com.downormal.moviesums.features.genres.data.repository

import com.downormal.moviesums.core.domain.DataError
import com.downormal.moviesums.core.domain.Result
import com.downormal.moviesums.core.domain.map
import com.downormal.moviesums.features.genres.data.mapper.toGenres
import com.downormal.moviesums.features.genres.data.remote.GenreRemoteDatasource
import com.downormal.moviesums.features.genres.domain.model.Genres
import com.downormal.moviesums.features.genres.domain.repository.GenresRepository
import javax.inject.Inject

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
class GenresRepositoryImpl @Inject constructor(
    private val datasource: GenreRemoteDatasource
) : GenresRepository {
    override suspend fun getGenres(): Result<Genres, DataError.Remote> {
        return datasource
            .getGenres()
            .map {  it.toGenres()  }
    }
}