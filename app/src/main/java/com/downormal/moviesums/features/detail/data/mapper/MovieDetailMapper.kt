package com.downormal.moviesums.features.detail.data.mapper

import com.downormal.moviesums.features.detail.data.dto.BelongsToCollectionDto
import com.downormal.moviesums.features.detail.data.dto.GenreDto
import com.downormal.moviesums.features.detail.data.dto.MovieDetailDto
import com.downormal.moviesums.features.detail.data.dto.ProductionCompanyDto
import com.downormal.moviesums.features.detail.data.dto.ProductionCountryDto
import com.downormal.moviesums.features.detail.data.dto.SpokenLanguageDto
import com.downormal.moviesums.features.detail.data.dto.VideoResultDto
import com.downormal.moviesums.features.detail.data.dto.VideosDto
import com.downormal.moviesums.features.detail.domain.model.BelongsToCollection
import com.downormal.moviesums.features.detail.domain.model.Genre
import com.downormal.moviesums.features.detail.domain.model.MovieDetail
import com.downormal.moviesums.features.detail.domain.model.ProductionCompany
import com.downormal.moviesums.features.detail.domain.model.ProductionCountry
import com.downormal.moviesums.features.detail.domain.model.SpokenLanguage
import com.downormal.moviesums.features.detail.domain.model.VideoResult
import com.downormal.moviesums.features.detail.domain.model.Videos


/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */
fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        adult = adult == true,
        backdropPath = backdropPath.orEmpty(),
        belongsToCollection = belongsToCollection?.toBelongsToCollection() ?: BelongsToCollection(0, "", "", ""),
        budget = budget ?: 0,
        genres = genres?.map { it.toGenre() } ?: emptyList(),
        homepage = homepage.orEmpty(),
        id = id,
        imdbId = imdbId.orEmpty(),
        originCountry = originCountry ?: emptyList(),
        originalLanguage = originalLanguage.orEmpty(),
        originalTitle = originalTitle.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: 0.0,
        posterPath = posterPath.orEmpty(),
        productionCompanies = productionCompanies?.map { it.toProductionCompany() } ?: emptyList(),
        productionCountries = productionCountries?.map { it.toProductionCountry() } ?: emptyList(),
        releaseDate = releaseDate.orEmpty(),
        revenue = revenue ?: 0L,
        runtime = runtime ?: 0,
        spokenLanguages = spokenLanguages?.map { it.toSpokenLanguage() } ?: emptyList(),
        status = status.orEmpty(),
        tagline = tagline.orEmpty(),
        title = title.orEmpty(),
        video = video == true,
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0,
        videos = videos?.toVideos() ?: Videos(emptyList())
    )
}

fun BelongsToCollectionDto.toBelongsToCollection() = BelongsToCollection(
    id = id,
    name = name.orEmpty(),
    posterPath = posterPath.orEmpty(),
    backdropPath = backdropPath.orEmpty()
)

fun GenreDto.toGenre() = Genre(
    id = id,
    name = name.orEmpty()
)

fun ProductionCompanyDto.toProductionCompany() = ProductionCompany(
    id = id,
    logoPath = logoPath.orEmpty(),
    name = name.orEmpty(),
    originCountry = originCountry.orEmpty()
)

fun ProductionCountryDto.toProductionCountry() = ProductionCountry(
    iso31661 = iso31661.orEmpty(),
    name = name.orEmpty()
)

fun SpokenLanguageDto.toSpokenLanguage() = SpokenLanguage(
    englishName = englishName.orEmpty(),
    iso6391 = iso6391.orEmpty(),
    name = name.orEmpty()
)

fun VideosDto.toVideos() = Videos(
    results = results?.map { it.toVideoResult() } ?: emptyList()
)

fun VideoResultDto.toVideoResult() = VideoResult(
    iso6391 = iso6391.orEmpty(),
    iso31661 = iso31661.orEmpty(),
    name = name.orEmpty(),
    key = key.orEmpty(),
    site = site.orEmpty(),
    size = size ?: 0,
    type = type.orEmpty(),
    official = official == true,
    publishedAt = publishedAt.orEmpty(),
    id = id.orEmpty()
)