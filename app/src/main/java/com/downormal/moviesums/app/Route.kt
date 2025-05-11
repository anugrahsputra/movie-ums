package com.downormal.moviesums.app

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
sealed interface Route {

    @Serializable
    data object Genres : Route

    @Serializable
    data object Movies : Route

    @Serializable
    data class MovieDetail(val id: Int) : Route
}