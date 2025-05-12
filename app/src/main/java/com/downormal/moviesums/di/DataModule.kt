package com.downormal.moviesums.di

import com.downormal.moviesums.features.genres.data.remote.GenreRemoteDatasource
import com.downormal.moviesums.features.genres.data.remote.GenreRemoteDatasourceImpl
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
}