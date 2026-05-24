package dev.training.bookshelf.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.training.bookshelf.data.BookRepository
import dev.training.bookshelf.model.Book
import dev.training.bookshelf.model.UiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the book list screen.
 *
 * Key concepts:
 * - ViewModel survives configuration changes (rotations, etc.)
 * - StateFlow exposes immutable state to the UI — the UI observes it.
 * - viewModelScope ties coroutines to the ViewModel's lifecycle.
 * - searchJob prevents multiple active Flow collectors after repeated searches.
 */
class BookListViewModel(
    private val repository: BookRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Book>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Book>>> = _uiState.asStateFlow()

    private var currentQuery: String = ""
    private var searchJob: Job? = null

    init {
        searchBooks("")
    }

    fun searchBooks(query: String) {
        currentQuery = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                repository.refreshBooks(query)
                repository.getBooks(query).collect { books ->
                    _uiState.value = UiState.Success(books)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    e.message ?: "An unexpected error occurred"
                )
            }
        }
    }

    fun retry() {
        searchBooks(currentQuery)
    }
}
