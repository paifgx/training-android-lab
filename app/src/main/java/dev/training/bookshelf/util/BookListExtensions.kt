package dev.training.bookshelf.util

import dev.training.bookshelf.model.Book

enum class BookSortMode {
    TITLE_ASC,
    YEAR_DESC
}

fun List<Book>.filterByQuery(query: String): List<Book> =
    if (query.isBlank()) this
    else filter { book ->
        book.title.contains(query, ignoreCase = true) ||
            book.authors.any { it.contains(query, ignoreCase = true) }
    }

fun List<Book>.sortedByMode(mode: BookSortMode): List<Book> =
    when (mode) {
        BookSortMode.TITLE_ASC -> sortedBy { it.title.lowercase() }
        BookSortMode.YEAR_DESC -> sortedByDescending { it.publishedDate.orEmpty() }
    }
