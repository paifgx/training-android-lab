package dev.training.bookshelf.util

/**
 * Extension functions for common string operations.
 *
 * Tips:
 * - Extension functions let you add methods to classes you don't own — like String.
 * - In Java you'd write a static utility method: StringUtils.isBlank(s).
 * - In Kotlin: s.isBlank() — reads naturally, no utility class needed.
 * - Extensions are resolved statically — they don't modify the class.
 */

fun String?.isNotNullOrBlank(): Boolean = !this.isNullOrBlank()

fun String.truncate(maxLength: Int): String =
    if (this.length <= maxLength) this
    else this.take(maxLength - 1) + "…"

/**
 * Format a list of author names into a readable string.
 */
fun List<String>.formatAuthors(): String =
    when {
        isEmpty() -> "Unknown Author"
        size == 1 -> first()
        size <= 3 -> joinToString(", ")
        else -> take(3).joinToString(", ") + " et al."
    }
