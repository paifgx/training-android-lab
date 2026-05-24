package dev.training.bookshelf.ui

import dev.training.bookshelf.data.FakeTestRepository
import dev.training.bookshelf.domain.RefreshBooksUseCase
import dev.training.bookshelf.domain.SearchBooksUseCase
import dev.training.bookshelf.model.Book
import dev.training.bookshelf.model.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val testBooks = listOf(
        Book("1", "Kotlin in Action", listOf("Jemerov"), "Desc", null, "2017"),
        Book("2", "Effective Java", listOf("Bloch"), "Desc", null, "2018"),
        Book("3", "Clean Code", listOf("Martin"), "Desc", null, "2008")
    )

    private lateinit var repository: FakeTestRepository
    private lateinit var viewModel: BookListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeTestRepository()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createViewModel(): BookListViewModel = BookListViewModel(
        searchBooksUseCase = SearchBooksUseCase(repository),
        refreshBooksUseCase = RefreshBooksUseCase(repository)
    )

    @Test
    fun `initial load with data succeeds`() = runTest(testDispatcher) {
        repository.setBooks(testBooks)
        viewModel = createViewModel()
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue("Expected Success, got $state", state is UiState.Success)
        assertEquals(3, (state as UiState.Success).data.size)
    }

    @Test
    fun `searchBooks filters by title`() = runTest(testDispatcher) {
        repository.setBooks(testBooks)
        viewModel = createViewModel()
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.searchBooks("Kotlin")
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue("Expected Success, got $state", state is UiState.Success)
        val books = (state as UiState.Success).data
        assertEquals(1, books.size)
        assertEquals("Kotlin in Action", books[0].title)
    }

    @Test
    fun `searchBooks with empty query returns all books`() = runTest(testDispatcher) {
        repository.setBooks(testBooks)
        viewModel = createViewModel()
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.searchBooks("")
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value as UiState.Success
        assertEquals(3, state.data.size)
    }

    @Test
    fun `searchBooks error state on repository failure`() = runTest(testDispatcher) {
        repository.setBooks(emptyList())
        repository.setErrorMode("Network timeout")
        viewModel = createViewModel()
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue("Expected Error, got $state", state is UiState.Error)
        assertTrue((state as UiState.Error).message.contains("Network timeout"))
    }

    @Test
    fun `retry repeats last search`() = runTest(testDispatcher) {
        repository.setBooks(testBooks)
        viewModel = createViewModel()
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.searchBooks("Clean")
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.retry()
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value as UiState.Success
        assertEquals(1, state.data.size)
        assertEquals("Clean Code", state.data[0].title)
    }
}
