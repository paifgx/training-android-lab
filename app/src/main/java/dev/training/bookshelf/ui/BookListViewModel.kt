package dev.training.bookshelf.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.training.bookshelf.domain.RefreshBooksUseCase
import dev.training.bookshelf.domain.SearchBooksUseCase
import dev.training.bookshelf.model.Book
import dev.training.bookshelf.model.BookResult
import dev.training.bookshelf.model.UiState
import dev.training.bookshelf.model.toUserMessage
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val searchBooks: SearchBooksUseCase,
    private val refreshBooks: RefreshBooksUseCase
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
            val refreshResult = refreshBooks(query)
            searchBooks(query).collect { books ->
                _uiState.value = when {
                    books.isNotEmpty() -> UiState.Success(books)
                    refreshResult is BookResult.Failure -> UiState.Error(refreshResult.error.toUserMessage())
                    else -> UiState.Success(books)
                }
            }
        }
    }

    fun retry() {
        searchBooks(currentQuery)
    }
}
