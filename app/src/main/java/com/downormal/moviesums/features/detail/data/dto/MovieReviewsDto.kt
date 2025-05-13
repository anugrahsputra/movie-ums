package com.downormal.moviesums.features.detail.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
@Serializable
data class MovieReviewsDto(
    @SerialName("id") val id: Int,
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<ReviewDto>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
)

@Serializable
data class ReviewDto(
    @SerialName("author") val author: String,
    @SerialName("author_details") val authorDetails: AuthorDetailsDto,
    @SerialName("content") val content: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("id") val id: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("url") val url: String

)

@Serializable
data class AuthorDetailsDto(
    @SerialName("name") val name: String,
    @SerialName("username") val username: String,
    @SerialName("avatar_path") val avatarPath: String? = null,
    @SerialName("rating") val rating: Double? = null
)