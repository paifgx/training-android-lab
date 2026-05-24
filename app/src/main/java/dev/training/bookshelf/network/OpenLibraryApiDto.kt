package dev.training.bookshelf.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * DTOs (Data Transfer Objects) for the Open Library Search API.
 *
 * These mirror the JSON response from:
 * https://openlibrary.org/search.json?q=kotlin&limit=20
 *
 * Keep DTOs separate from domain models. The API shape is not the same thing as
 * the app's domain model.
 */
@JsonClass(generateAdapter = true)
data class OpenLibrarySearchResponse(
    @Json(name = "docs") val docs: List<OpenLibraryBookDto>?
)

@JsonClass(generateAdapter = true)
data class OpenLibraryBookDto(
    @Json(name = "key") val key: String?,
    @Json(name = "title") val title: String?,
    @Json(name = "author_name") val authors: List<String>?,
    @Json(name = "first_publish_year") val firstPublishYear: Int?,
    @Json(name = "cover_i") val coverId: Int?,
    @Json(name = "edition_count") val editionCount: Int?,
    @Json(name = "first_sentence") val firstSentence: List<String>?
)
