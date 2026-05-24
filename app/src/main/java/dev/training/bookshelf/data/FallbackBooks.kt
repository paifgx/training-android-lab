package dev.training.bookshelf.data

import dev.training.bookshelf.model.Book

/**
 * Local fallback data for workshop reliability.
 *
 * The Open Library API can return HTTP 429 without an API key, especially when
 * a training group sends many requests from the same network. The app should
 * still be demonstrable, so the repository can fall back to this in-memory list.
 */
internal object FallbackBooks {

    private val books = listOf(
        Book(
            id = "fallback-kotlin-in-action",
            title = "Kotlin in Action",
            authors = listOf("Dmitry Jemerov", "Svetlana Isakova"),
            description = "A practical Kotlin introduction for developers coming from Java.",
            thumbnailUrl = null,
            publishedDate = "2017"
        ),
        Book(
            id = "fallback-effective-java",
            title = "Effective Java",
            authors = listOf("Joshua Bloch"),
            description = "A Java classic and useful bridge for comparing Java and Kotlin idioms.",
            thumbnailUrl = null,
            publishedDate = "2018"
        ),
        Book(
            id = "fallback-android-programming",
            title = "Android Programming: The Big Nerd Ranch Guide",
            authors = listOf("Bill Phillips", "Chris Stewart", "Kristin Marsicano"),
            description = "Android fundamentals, lifecycle, UI, and architecture building blocks.",
            thumbnailUrl = null,
            publishedDate = "2022"
        ),
        Book(
            id = "fallback-clean-code",
            title = "Clean Code",
            authors = listOf("Robert C. Martin"),
            description = "A software craftsmanship reference that transfers well to Android codebases.",
            thumbnailUrl = null,
            publishedDate = "2008"
        ),
        Book(
            id = "fallback-android-architecture",
            title = "Modern Android Architecture",
            authors = listOf("Training Android Lab"),
            description = "Fallback entry covering MVVM, Repository, Room, Retrofit, Hilt, and testing.",
            thumbnailUrl = null,
            publishedDate = "2026"
        )
    )

    fun search(query: String): List<Book> {
        val trimmed = query.trim()
        if (trimmed.isBlank()) return emptyList()

        val matches = books.filter { book ->
            book.title.contains(trimmed, ignoreCase = true) ||
                book.authors.any { it.contains(trimmed, ignoreCase = true) } ||
                book.description.contains(trimmed, ignoreCase = true)
        }

        return matches.ifEmpty { books }
    }
}
