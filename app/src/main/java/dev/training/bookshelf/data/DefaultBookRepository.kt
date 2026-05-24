package dev.training.bookshelf.data

import dev.training.bookshelf.local.BookDao
import dev.training.bookshelf.local.toDomainModel
import dev.training.bookshelf.local.toDomainModels
import dev.training.bookshelf.local.toEntities
import dev.training.bookshelf.model.Book
import dev.training.bookshelf.model.BookResult
import dev.training.bookshelf.network.OpenLibraryApiService
import dev.training.bookshelf.network.toDomainModels
import dev.training.bookshelf.network.toNetworkError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultBookRepository(
    private val apiService: OpenLibraryApiService,
    private val bookDao: BookDao
) : BookRepository {

    override fun getBooks(query: String): Flow<List<Book>> =
        bookDao.getBooksByQuery(query).map { entities -> entities.toDomainModels() }

    override fun getBook(id: String): Flow<Book?> =
        bookDao.getBookById(id).map { it?.toDomainModel() }

    override suspend fun refreshBooks(query: String): BookResult<Unit> {
        if (query.isBlank()) return BookResult.Success(Unit)

        return runCatching { apiService.searchBooks(query).toDomainModels() }
            .fold(
                onSuccess = { books ->
                    bookDao.deleteByQuery(query)
                    bookDao.insertAll(books.toEntities(query))
                    BookResult.Success(Unit)
                },
                onFailure = { throwable ->
                    val fallback = FallbackBooks.search(query)
                    if (fallback.isNotEmpty()) {
                        bookDao.deleteByQuery(query)
                        bookDao.insertAll(fallback.toEntities(query))
                    }
                    BookResult.Failure((throwable as? Exception)?.toNetworkError() ?: dev.training.bookshelf.model.NetworkError.Unknown)
                }
            )
    }
}
