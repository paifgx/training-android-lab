package dev.training.bookshelf.data

import dev.training.bookshelf.model.Book
import dev.training.bookshelf.model.BookResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import dev.training.bookshelf.util.BookSortMode
import dev.training.bookshelf.util.filterByQuery
import dev.training.bookshelf.util.sortedByMode
import kotlinx.coroutines.flow.map

/**
 * Fake in-memory implementation of BookRepository.
 *
 * Purpose:
 * - No network, no database — just hardcoded data.
 * - Lets you build and test the ViewModel + UI without any infrastructure.
 * - In a real app, you'd have a "real" repository backed by Retrofit + Room.
 *
 * Tips:
 * - MutableStateFlow is a hot Flow that holds a value and emits updates.
 * - `.map { }` transforms the emitted values — like Java Streams, but reactive.
 */
class FakeBookRepository : BookRepository {

    private val books = MutableStateFlow<List<Book>>(emptyList())

    init {
        // Pre-populate with sample data
        books.value = listOf(
            Book(
                id = "1",
                title = "Effective Java",
                authors = listOf("Joshua Bloch"),
                description = "A must-read for any Java developer transitioning to best practices.",
                thumbnailUrl = null,
                publishedDate = "2018"
            ),
            Book(
                id = "2",
                title = "Kotlin in Action",
                authors = listOf("Dmitry Jemerov", "Svetlana Isakova"),
                description = "The definitive guide to Kotlin, from the team at JetBrains.",
                thumbnailUrl = null,
                publishedDate = "2017"
            ),
            Book(
                id = "3",
                title = "Clean Code",
                authors = listOf("Robert C. Martin"),
                description = "A handbook of agile software craftsmanship.",
                thumbnailUrl = null,
                publishedDate = "2008"
            )
        )
    }

    override fun getBooks(query: String): Flow<List<Book>> =
        books.map { bookList ->
            bookList
                .filterByQuery(query)
                .sortedByMode(BookSortMode.TITLE_ASC)
        }

    override fun getBook(id: String): Flow<Book?> =
        books.map { it.find { book -> book.id == id } }

    override suspend fun refreshBooks(query: String): BookResult<Unit> =
        BookResult.Success(Unit)
}
