package dev.training.bookshelf.data

import dev.training.bookshelf.model.Book
import dev.training.bookshelf.model.BookResult
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface — the single source of truth for book data.
 */
interface BookRepository {
    fun getBooks(query: String): Flow<List<Book>>
    fun getBook(id: String): Flow<Book?>
    suspend fun refreshBooks(query: String): BookResult<Unit>
}
