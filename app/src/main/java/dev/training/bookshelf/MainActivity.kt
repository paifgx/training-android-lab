package dev.training.bookshelf

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.training.bookshelf.databinding.ActivityMainBinding
import dev.training.bookshelf.model.UiState
import dev.training.bookshelf.ui.BookAdapter
import dev.training.bookshelf.ui.BookListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * MainActivity with @AndroidEntryPoint.
 *
 * @AndroidEntryPoint = tells Hilt that this Activity can use Hilt-created dependencies.
 * The ViewModel is obtained via by viewModels(), so Hilt creates it correctly.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: BookListViewModel by viewModels()
    private val adapter = BookAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSearch()
        observeUiState()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewBooks.layoutManager = LinearLayoutManager(this)
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
