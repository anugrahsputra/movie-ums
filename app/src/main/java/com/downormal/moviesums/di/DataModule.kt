package com.downormal.moviesums.di

import com.downormal.moviesums.features.detail.data.remote.MovieDetailDatasource
import com.downormal.moviesums.features.detail.data.remote.MovieDetailDatasourceImpl
import com.downormal.moviesums.features.genres.data.remote.GenreRemoteDatasource
import com.downormal.moviesums.features.genres.data.remote.GenreRemoteDatasourceImpl
import com.downormal.moviesums.features.movies.data.remote.MovieListDataSource
import com.downormal.moviesums.features.movies.data.remote.MovieListDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindGenreRemoteDatasource(
        impl: GenreRemoteDatasourceImpl
    ): GenreRemoteDatasource

    @Binds
    @Singleton
    abstract fun bindMovieListDatasource(
        impl: MovieListDataSourceImpl
    ) : MovieListDataSource

    @Binds
    @Singleton
    abstract fun bindMovieDetailDatasource(
        impl: MovieDetailDatasourceImpl
    ) : MovieDetailDatasource

}