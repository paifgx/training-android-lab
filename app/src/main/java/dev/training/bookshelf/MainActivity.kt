package dev.training.bookshelf

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dev.training.bookshelf.data.FakeBookRepository
import dev.training.bookshelf.databinding.ActivityMainBinding
import dev.training.bookshelf.domain.RefreshBooksUseCase
import dev.training.bookshelf.domain.SearchBooksUseCase
import dev.training.bookshelf.model.UiState
import dev.training.bookshelf.ui.BookAdapter
import dev.training.bookshelf.ui.BookListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: BookListViewModel
    private val adapter = BookAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = FakeBookRepository()
        viewModel = BookListViewModel(
            searchBooksUseCase = SearchBooksUseCase(repository),
            refreshBooksUseCase = RefreshBooksUseCase(repository)
        )

        setupRecyclerView()
        setupSearch()
        observeUiState()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewBooks.adapter = adapter
    }

    private fun setupSearch() {
        binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH) {
                viewModel.searchBooks(binding.searchEditText.text.toString())
                true
            } else {
                false
            }
        }
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { state ->
                    when (state) {
                        is UiState.Loading -> {
                            binding.progressBar.isVisible = true
                            binding.errorContainer.isVisible = false
                            binding.textViewEmpty.isVisible = false
                            binding.recyclerViewBooks.isVisible = false
                        }
                        is UiState.Success -> {
                            binding.progressBar.isVisible = false
                            binding.recyclerViewBooks.isVisible = true
                            binding.errorContainer.isVisible = false
                            binding.textViewEmpty.isVisible = state.data.isEmpty()
                            adapter.submitList(state.data)
                        }
                        is UiState.Error -> {
                            binding.progressBar.isVisible = false
                            binding.recyclerViewBooks.isVisible = false
                            binding.textViewEmpty.isVisible = false
                            binding.errorContainer.isVisible = true
                            binding.textViewError.text = state.message
                        }
                    }
                }
            }
        }

        binding.buttonRetry.setOnClickListener {
            viewModel.retry()
        }
    }
}
