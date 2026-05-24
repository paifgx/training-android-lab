package dev.training.bookshelf.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExtensionsTest {

    @Test
    fun `isNotNullOrBlank with non-null non-blank string`() {
        assertTrue("hello".isNotNullOrBlank())
    }

    @Test
    fun `isNotNullOrBlank with null`() {
        assertFalse(null.isNotNullOrBlank())
    }

    @Test
    fun `isNotNullOrBlank with blank string`() {
        assertFalse("   ".isNotNullOrBlank())
    }

    @Test
    fun `truncate short string unchanged`() {
        assertEquals("hello", "hello".truncate(10))
    }

    @Test
    fun `truncate long string with ellipsis`() {
        val result = "Hello World".truncate(8)
        assertEquals(8, result.length)
        assertTrue(result.endsWith("…"))
    }

    @Test
    fun `formatAuthors empty list`() {
        assertEquals("Unknown Author", emptyList<String>().formatAuthors())
    }

    @Test
    fun `formatAuthors single author`() {
        assertEquals("Bloch", listOf("Bloch").formatAuthors())
    }

    @Test
    fun `formatAuthors two authors`() {
        assertEquals("Bloch, Martin", listOf("Bloch", "Martin").formatAuthors())
    }

    @Test
    fun `formatAuthors four authors truncates`() {
        val result = listOf("A", "B", "C", "D").formatAuthors()
        assertEquals("A, B, C et al.", result)
    }
}
