package dev.training.bookshelf.network

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service interface for the Open Library Search API.
 *
 * Open Library is useful for workshops because it is public and does not require
 * an API key for this search endpoint.
 */
interface OpenLibraryApiService {

    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("limit") limit: Int = 20,
        @Query("fields") fields: String = DEFAULT_FIELDS
    ): OpenLibrarySearchResponse

    companion object {
        const val DEFAULT_FIELDS =
            "key,title,author_name,first_publish_year,cover_i,edition_count,first_sentence"
    }
}
