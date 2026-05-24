package dev.training.bookshelf.data

import dev.training.bookshelf.local.BookDao
import dev.training.bookshelf.local.toDomainModel
import dev.training.bookshelf.local.toDomainModels
import dev.training.bookshelf.local.toEntities
import dev.training.bookshelf.model.Book
import dev.training.bookshelf.network.OpenLibraryApiService
import dev.training.bookshelf.network.toDomainModels
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Real repository implementation: API → Room → UI.
 *
 * The UI observes Room. The repository refreshes Room from the API.
 * If the live API is unavailable or rate-limited, local fallback books keep the
 * training app usable without changing the UI or ViewModel.
 */
class DefaultBookRepository(
    private val apiService: OpenLibraryApiService,
    private val bookDao: BookDao
) : BookRepository {

    override fun getBooks(query: String): Flow<List<Book>> =
        bookDao.getBooksByQuery(query).map { entities ->
            entities.toDomainModels()
        }

    override fun getBook(id: String): Flow<Book?> =
        bookDao.getBookById(id).map { it?.toDomainModel() }

    override suspend fun refreshBooks(query: String) {
        if (query.isBlank()) return

        val books = runCatching {
            apiService.searchBooks(query).toDomainModels()
        }.getOrElse {
            FallbackBooks.search(query)
        }

        bookDao.deleteByQuery(query)
        bookDao.insertAll(books.toEntities(query))
    }
}
