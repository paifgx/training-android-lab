package dev.training.bookshelf.data

import dev.training.bookshelf.model.Book
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface — the single source of truth for book data.
 *
 * Why an interface?
 * - Decouples the ViewModel from the data source.
 * - You can swap implementations: fake for testing, real for production.
 * - This is the Repository pattern — the ViewModel doesn't know or care
 *   where the data comes from (network, database, in-memory).
 */
interface BookRepository {
    fun getBooks(query: String): Flow<List<Book>>
    fun getBook(id: String): Flow<Book?>
    suspend fun refreshBooks(query: String)
}
