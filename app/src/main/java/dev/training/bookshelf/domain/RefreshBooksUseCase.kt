package dev.training.bookshelf.domain

import dev.training.bookshelf.data.BookRepository
import dev.training.bookshelf.model.BookResult
import javax.inject.Inject

class RefreshBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    suspend operator fun invoke(query: String): BookResult<Unit> =
        repository.refreshBooks(query)
}
