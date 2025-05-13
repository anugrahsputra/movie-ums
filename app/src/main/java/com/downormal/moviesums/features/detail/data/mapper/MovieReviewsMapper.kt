package com.downormal.moviesums.features.detail.data.mapper

import com.downormal.moviesums.features.detail.data.dto.AuthorDetailsDto
import com.downormal.moviesums.features.detail.data.dto.MovieReviewsDto
import com.downormal.moviesums.features.detail.data.dto.ReviewDto
import com.downormal.moviesums.features.detail.domain.model.AuthorDetails
import com.downormal.moviesums.features.detail.domain.model.MovieReviews
import com.downormal.moviesums.features.detail.domain.model.Review

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */

fun MovieReviewsDto.toMovieReviews(): MovieReviews {
    return MovieReviews(
        id = id,
        page = page,
        results = results.map { it.toReview() },
        totalPages = totalPages,
        totalResults = totalResults
    )
}

fun ReviewDto.toReview(): Review {
    return Review(
        author = author,
        authorDetails = authorDetails.toAuthorDetails(),
        content = content,
        createdAt = createdAt,
        id = id,
        updatedAt = updatedAt,
        url = url
    )
}

fun AuthorDetailsDto.toAuthorDetails(): AuthorDetails {
    return AuthorDetails(
        name = name,
        username = username,
        avatarPath = avatarPath ?: "",
        rating = rating ?: 0.0
    )
}