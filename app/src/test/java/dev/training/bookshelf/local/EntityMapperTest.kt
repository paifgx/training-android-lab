package dev.training.bookshelf.local

import dev.training.bookshelf.model.Book
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unit tests for EntityMapper (domain ↔ entity conversion).
 *
 * Tips:
 * - These are plain unit tests — no Android context, no database needed.
 * - Test the mapping logic in isolation.
 * - When migrating from GreenDao to Room, you'd write exactly these tests
 *   to verify the mapping doesn't lose data.
 */
class EntityMapperTest {

    private val testBook = Book(
        id = "abc123",
        title = "Test Book",
        authors = listOf("Author A", "Author B"),
        description = "A test book",
        thumbnailUrl = "https://example.com/cover.jpg",
        publishedDate = "2024"
    )

    @Test
    fun `Book to Entity preserves all fields`() {
        val entity = testBook.toEntity("test query")

        assertEquals(testBook.id, entity.id)
        assertEquals(testBook.title, entity.title)
        assertEquals(testBook.authors, entity.authors)
        assertEquals(testBook.description, entity.description)
        assertEquals(testBook.thumbnailUrl, entity.thumbnailUrl)
        assertEquals(testBook.publishedDate, entity.publishedDate)
        assertEquals("test query", entity.lastSearchQuery)
    }

    @Test
    fun `Entity to domain Book preserves all fields`() {
        val entity = testBook.toEntity("query")
        val domainBook = entity.toDomainModel()

        assertEquals(testBook.id, domainBook.id)
        assertEquals(testBook.title, domainBook.title)
        assertEquals(testBook.authors, domainBook.authors)
        assertEquals(testBook.description, domainBook.description)
        assertEquals(testBook.thumbnailUrl, domainBook.thumbnailUrl)
        assertEquals(testBook.publishedDate, domainBook.publishedDate)
    }

    @Test
    fun `Round-trip conversion is lossless`() {
        val entity = testBook.toEntity("round-trip")
        val roundTripped = entity.toDomainModel()

        assertEquals(testBook, roundTripped)
    }

    @Test
    fun `List extension maps all items`() {
        val books = listOf(testBook, testBook.copy(id = "xyz"))
        val entities = books.toEntities("bulk")

        assertEquals(2, entities.size)
        assertEquals("bulk", entities[0].lastSearchQuery)
    }

    @Test
    fun `Entity with null thumbnail maps correctly`() {
        val bookNoCover = testBook.copy(thumbnailUrl = null)
        val entity = bookNoCover.toEntity("q")
        val result = entity.toDomainModel()

        assertEquals(null, result.thumbnailUrl)
    }
}
