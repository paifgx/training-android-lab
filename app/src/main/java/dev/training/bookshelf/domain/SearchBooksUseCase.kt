package dev.training.bookshelf.domain

import dev.training.bookshelf.data.BookRepository
import dev.training.bookshelf.model.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    operator fun invoke(query: String): Flow<List<Book>> =
        repository.getBooks(query)
}
