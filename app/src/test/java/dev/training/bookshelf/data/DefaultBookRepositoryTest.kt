package dev.training.bookshelf.data

import dev.training.bookshelf.local.BookDao
import dev.training.bookshelf.local.BookEntity
import dev.training.bookshelf.model.BookResult
import dev.training.bookshelf.network.OpenLibraryApiService
import dev.training.bookshelf.network.OpenLibraryBookDto
import dev.training.bookshelf.network.OpenLibrarySearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class DefaultBookRepositoryTest {

    @Test
    fun `refreshBooks writes API results into DAO`() = runTest {
        val api = FakeOpenLibraryApiService(
            response = OpenLibrarySearchResponse(
                docs = listOf(
                    OpenLibraryBookDto(
                        key = "/works/OL1W",
                        title = "Kotlin in Action",
                        authors = listOf("Author"),
                        firstPublishYear = 2017,
                        coverId = 123,
                        editionCount = 1,
                        firstSentence = listOf("Kotlin book")
                    )
                )
            )
        )
        val dao = FakeBookDao()
        val repository = DefaultBookRepository(api, dao)

        val result = repository.refreshBooks("kotlin")

        assertTrue(result is BookResult.Success)
        assertEquals(1, dao.currentBooks().size)
        assertEquals("Kotlin in Action", dao.currentBooks().first().title)
        assertEquals(1, api.callCount)
    }

    @Test
    fun `refreshBooks returns failure and writes fallback on API error`() = runTest {
        val api = FakeOpenLibraryApiService(exception = IOException("offline"))
        val dao = FakeBookDao()
        val repository = DefaultBookRepository(api, dao)

        val result = repository.refreshBooks("kotlin")

        assertTrue(result is BookResult.Failure)
        assertTrue(dao.currentBooks().isNotEmpty())
        assertEquals(1, api.callCount)
    }

    @Test
    fun `blank query does not call API`() = runTest {
        val api = FakeOpenLibraryApiService()
        val dao = FakeBookDao()
        val repository = DefaultBookRepository(api, dao)

        val result = repository.refreshBooks("   ")

        assertTrue(result is BookResult.Success)
        assertEquals(0, api.callCount)
        assertTrue(dao.currentBooks().isEmpty())
    }
}

private class FakeOpenLibraryApiService(
    private val response: OpenLibrarySearchResponse = OpenLibrarySearchResponse(docs = emptyList()),
    private val exception: Exception? = null
) : OpenLibraryApiService {

    var callCount: Int = 0
        private set

    override suspend fun searchBooks(
        query: String,
        limit: Int,
        fields: String
    ): OpenLibrarySearchResponse {
        callCount++
        exception?.let { throw it }
        return response
    }
}

private class FakeBookDao : BookDao {

    private val books = MutableStateFlow<List<BookEntity>>(emptyList())

    fun currentBooks(): List<BookEntity> = books.value

    override fun getBooksByQuery(query: String): Flow<List<BookEntity>> =
        books.map { list ->
            if (query.isBlank()) list
            else list.filter { it.lastSearchQuery.contains(query, ignoreCase = true) }
        }

    override fun getBookById(id: String): Flow<BookEntity?> =
        books.map { list -> list.find { it.id == id } }

    override fun getAllBooks(): Flow<List<BookEntity>> = books

    override suspend fun insertAll(books: List<BookEntity>) {
        this.books.value = books
    }

    override suspend fun deleteByQuery(query: String) {
        books.value = books.value.filterNot { it.lastSearchQuery.contains(query, ignoreCase = true) }
    }

    override suspend fun deleteAll() {
        books.value = emptyList()
    }
}
