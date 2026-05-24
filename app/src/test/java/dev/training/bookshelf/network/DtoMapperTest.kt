package dev.training.bookshelf.network

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unit tests for the Open Library DTO → domain model mapper.
 *
 * These tests verify null handling and mapping without network calls.
 */
class DtoMapperTest {

    @Test
    fun `full DTO maps to Book correctly`() {
        val response = OpenLibrarySearchResponse(
            docs = listOf(
                OpenLibraryBookDto(
                    key = "/works/OL123W",
                    title = "Kotlin in Action",
                    authors = listOf("Dmitry Jemerov", "Svetlana Isakova"),
                    firstPublishYear = 2017,
                    coverId = 12345,
                    editionCount = 4,
                    firstSentence = listOf("A practical Kotlin introduction.")
                )
            )
        )

        val books = response.toDomainModels()

        assertEquals(1, books.size)
        assertEquals("OL123W", books[0].id)
        assertEquals("Kotlin in Action", books[0].title)
        assertEquals(listOf("Dmitry Jemerov", "Svetlana Isakova"), books[0].authors)
        assertEquals("2017", books[0].publishedDate)
        assertEquals("https://covers.openlibrary.org/b/id/12345-M.jpg", books[0].thumbnailUrl)
        assertEquals("A practical Kotlin introduction.", books[0].description)
    }

    @Test
    fun `null docs returns empty list`() {
        val response = OpenLibrarySearchResponse(docs = null)
        val books = response.toDomainModels()
        assertTrue(books.isEmpty())
    }

    @Test
    fun `empty docs returns empty list`() {
        val response = OpenLibrarySearchResponse(docs = emptyList())
        val books = response.toDomainModels()
        assertTrue(books.isEmpty())
    }

    @Test
    fun `null key is filtered out`() {
        val response = OpenLibrarySearchResponse(
            docs = listOf(
                OpenLibraryBookDto(
                    key = null,
                    title = "No Key Book",
                    authors = listOf("Author"),
                    firstPublishYear = 2024,
                    coverId = null,
                    editionCount = null,
                    firstSentence = null
                )
            )
        )

        val books = response.toDomainModels()
        assertTrue(books.isEmpty())
    }

    @Test
    fun `null title is filtered out`() {
        val response = OpenLibrarySearchResponse(
            docs = listOf(
                OpenLibraryBookDto(
                    key = "/works/OL1W",
                    title = null,
                    authors = listOf("Author"),
                    firstPublishYear = 2024,
                    coverId = null,
                    editionCount = null,
                    firstSentence = null
                )
            )
        )

        val books = response.toDomainModels()
        assertTrue(books.isEmpty())
    }

    @Test
    fun `null authors defaults to empty list`() {
        val response = OpenLibrarySearchResponse(
            docs = listOf(
                OpenLibraryBookDto(
                    key = "/works/OL2W",
                    title = "No Authors Book",
                    authors = null,
                    firstPublishYear = 2024,
                    coverId = null,
                    editionCount = 1,
                    firstSentence = null
                )
            )
        )

        val books = response.toDomainModels()
        assertEquals(1, books.size)
        assertEquals(emptyList<String>(), books[0].authors)
    }

    @Test
    fun `null coverId maps to null thumbnail`() {
        val response = OpenLibrarySearchResponse(
            docs = listOf(
                OpenLibraryBookDto(
                    key = "/works/OL3W",
                    title = "No Cover Book",
                    authors = listOf("Author"),
                    firstPublishYear = 2024,
                    coverId = null,
                    editionCount = null,
                    firstSentence = null
                )
            )
        )

        val books = response.toDomainModels()
        assertEquals(1, books.size)
        assertNull(books[0].thumbnailUrl)
    }

    @Test
    fun `description falls back to edition count`() {
        val response = OpenLibrarySearchResponse(
            docs = listOf(
                OpenLibraryBookDto(
                    key = "/works/OL4W",
                    title = "Edition Count Book",
                    authors = listOf("Author"),
                    firstPublishYear = null,
                    coverId = null,
                    editionCount = 7,
                    firstSentence = null
                )
            )
        )

        val books = response.toDomainModels()
        assertEquals("Open Library entry with 7 edition(s).", books[0].description)
    }
}
