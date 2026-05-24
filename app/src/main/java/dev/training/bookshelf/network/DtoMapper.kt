package dev.training.bookshelf.network

import dev.training.bookshelf.model.Book

/**
 * Maps Open Library DTOs to domain models.
 *
 * Null-safety is handled here once. The rest of the app works with clean domain
 * models and does not need to know how Open Library structures JSON.
 */
fun OpenLibrarySearchResponse.toDomainModels(): List<Book> =
    docs.orEmpty()
        .mapNotNull { it.toDomainModel() }
        .distinctBy { it.id }

private fun OpenLibraryBookDto.toDomainModel(): Book? {
    val bookKey = key?.removePrefix("/works/")?.takeIf { it.isNotBlank() } ?: return null
    val bookTitle = title?.takeIf { it.isNotBlank() } ?: return null

    return Book(
        id = bookKey,
        title = bookTitle,
        authors = authors.orEmpty(),
        description = firstSentence?.firstOrNull { it.isNotBlank() }
            ?: editionCount?.let { count -> "Open Library entry with $count edition(s)." }
            ?: "",
        thumbnailUrl = coverId?.let { id -> "https://covers.openlibrary.org/b/id/$id-M.jpg" },
        publishedDate = firstPublishYear?.toString()
    )
}
