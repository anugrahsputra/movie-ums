package com.downormal.moviesums.features.detail.domain.model

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
data class MovieReviews(
    val id: Int,
    val page: Int,
    val results: List<Review>,
    val totalPages: Int,
    val totalResults: Int
)

data class Review(
    val author: String,
    val authorDetails: AuthorDetails,
    val content: String,
    val createdAt: String,
    val id: String,
    val updatedAt: String,
    val url: String

)

data class AuthorDetails(
    val name: String,
    val username: String,
    val avatarPath: String,
    val rating: Double
)