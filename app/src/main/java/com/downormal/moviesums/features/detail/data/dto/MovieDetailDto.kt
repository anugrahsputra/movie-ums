package com.downormal.moviesums.features.detail.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Code author  : Anugrah Surya Putra.
 * Project      : MoviesUMS
 */

@Serializable
data class MovieDetailDto(
    @SerialName("adult") val adult: Boolean?,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("belongs_to_collection") val belongsToCollection: BelongsToCollectionDto?,
    @SerialName("budget") val budget: Int?,
    @SerialName("genres") val genres: List<GenreDto>?,
    @SerialName("homepage") val homepage: String?,
    @SerialName("id") val id: Int,
    @SerialName("imdb_id") val imdbId: String?,
    @SerialName("origin_country") val originCountry: List<String>?,
    @SerialName("original_language") val originalLanguage: String?,
    @SerialName("original_title") val originalTitle: String?,
    @SerialName("overview") val overview: String?,
    @SerialName("popularity") val popularity: Double?,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("production_companies") val productionCompanies: List<ProductionCompanyDto>?,
    @SerialName("production_countries") val productionCountries: List<ProductionCountryDto>?,
    @SerialName("release_date") val releaseDate: String?,
    @SerialName("revenue") val revenue: Long?,
    @SerialName("runtime") val runtime: Int?,
    @SerialName("spoken_languages") val spokenLanguages: List<SpokenLanguageDto>?,
    @SerialName("status") val status: String?,
    @SerialName("tagline") val tagline: String?,
    @SerialName("title") val title: String?,
    @SerialName("video") val video: Boolean?,
    @SerialName("vote_average") val voteAverage: Double?,
    @SerialName("vote_count") val voteCount: Int?,
    @SerialName("videos") val videos: VideosDto?
)

@Serializable
data class BelongsToCollectionDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String?,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("backdrop_path") val backdropPath: String?
)

@Serializable
data class GenreDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String?
)

@Serializable
data class ProductionCompanyDto(
    @SerialName("id") val id: Int,
    @SerialName("logo_path") val logoPath: String?,
    @SerialName("name") val name: String?,
    @SerialName("origin_country") val originCountry: String?
)

@Serializable
data class ProductionCountryDto(
    @SerialName("iso_3166_1") val iso31661: String?,
    @SerialName("name") val name: String?
)

@Serializable
data class SpokenLanguageDto(
    @SerialName("english_name") val englishName: String?,
    @SerialName("iso_639_1") val iso6391: String?,
    @SerialName("name") val name: String?
)

@Serializable
data class VideosDto(
    @SerialName("results") val results: List<VideoResultDto>?
)

@Serializable
data class VideoResultDto(
    @SerialName("iso_639_1") val iso6391: String?,
    @SerialName("iso_3166_1") val iso31661: String?,
    @SerialName("name") val name: String?,
    @SerialName("key") val key: String?,
    @SerialName("site") val site: String?,
    @SerialName("size") val size: Int?,
    @SerialName("type") val type: String?,
    @SerialName("official") val official: Boolean?,
    @SerialName("published_at") val publishedAt: String?,
    @SerialName("id") val id: String?
)