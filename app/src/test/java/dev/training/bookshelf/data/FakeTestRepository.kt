package dev.training.bookshelf.data

import dev.training.bookshelf.model.Book
import dev.training.bookshelf.model.BookResult
import dev.training.bookshelf.model.NetworkError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

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

    override suspend fun refreshBooks(query: String): BookResult<Unit> =
        if (shouldFail) BookResult.Failure(NetworkError.HttpError(599, errorMessage))
        else BookResult.Success(Unit)
}
