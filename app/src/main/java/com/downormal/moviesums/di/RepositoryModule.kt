package com.downormal.moviesums.di

import com.downormal.moviesums.features.genres.data.repository.GenresRepositoryImpl
import com.downormal.moviesums.features.genres.domain.repository.GenresRepository
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
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindGenresRepository(
        impl: GenresRepositoryImpl
    ): GenresRepository
}