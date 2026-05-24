package dev.training.bookshelf.domain

import dev.training.bookshelf.data.BookRepository
import dev.training.bookshelf.model.Book
import kotlinx.coroutines.flow.Flow

class SearchBooksUseCase(
    private val repository: BookRepository
) {
    operator fun invoke(query: String): Flow<List<Book>> =
        repository.getBooks(query)
}
