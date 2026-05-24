package dev.training.bookshelf.data

import dev.training.bookshelf.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

/**
 * Fake BookRepository for unit tests.
 *
 * Same as FakeBookRepository from task/02, but now used for testing
 * the ViewModel instead of shipping in production code.
 *
 * Tips:
 * - In tests, you control exactly what data the repository returns.
 * - No network, no database — instant, deterministic, no flakiness.
 * - This is why the Repository interface matters: swap production for fake in tests.
 * - You can add methods like setErrorMode() to simulate failures.
 */
class FakeTestRepository : BookRepository {

    private val books = MutableStateFlow<List<Book>>(emptyList())
    private var shouldFail = false
    private var errorMessage = "Test error"

    fun setBooks(books: List<Book>) {
        this.books.value = books
    }

    fun setErrorMode(message: String = "Test error") {
        shouldFail = true
        errorMessage = message
    }

    override fun getBooks(query: String): Flow<List<Book>> =
        books.map { bookList ->
            if (query.isBlank()) bookList
            else bookList.filter {
                it.title.contains(query, ignoreCase = true) ||
                    it.authors.any { a -> a.contains(query, ignoreCase = true) }
            }
        }

    override fun getBook(id: String): Flow<Book?> =
        books.map { it.find { book -> book.id == id } }

    override suspend fun refreshBooks(query: String) {
        if (shouldFail) {
            throw RuntimeException(errorMessage)
        }
        // No-op: test data is set via setBooks()
    }
}
