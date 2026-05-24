package dev.training.bookshelf.data

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FallbackBooksTest {

    @Test
    fun `blank query returns no fallback books`() {
        assertTrue(FallbackBooks.search("   ").isEmpty())
    }

    @Test
    fun `matching query returns matching fallback books`() {
        val result = FallbackBooks.search("kotlin")
        assertFalse(result.isEmpty())
        assertTrue(result.any { it.title.contains("Kotlin", ignoreCase = true) })
    }

    @Test
    fun `unknown query still returns useful training fallback set`() {
        val result = FallbackBooks.search("unlikely-query-for-training")
        assertFalse(result.isEmpty())
    }
}
