package com.downormal.moviesums.core.domain

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */

interface Error

sealed interface DataError: Error {
    enum class Remote: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN
    }

    enum class Local: DataError {
        DISK_FULL,
        UNKNOWN
    }
}