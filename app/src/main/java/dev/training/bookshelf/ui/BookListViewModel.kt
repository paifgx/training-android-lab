package dev.training.bookshelf.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.training.bookshelf.data.BookRepository
import dev.training.bookshelf.model.Book
import dev.training.bookshelf.model.UiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel annotated with @HiltViewModel.
 *
 * @HiltViewModel = tells Hilt to create this ViewModel via injection.
 * @Inject constructor = Hilt provides the BookRepository automatically.
 * searchJob prevents multiple active Flow collectors after repeated searches.
 */
@HiltViewModel
class BookListViewModel @Inject constructor(
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
