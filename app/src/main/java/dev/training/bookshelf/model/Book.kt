package dev.training.bookshelf.model

/**
 * Represents a book in the Bookshelf domain.
 *
 * Tips:
 * - In Java, this would be a POJO with getters/setters, a constructor, equals/hashCode/toString.
 * - In Kotlin, `data class` gives you all of that automatically.
 * - `val` = read-only, `var` = mutable. Prefer `val`.
 * - Default values in the constructor = no need for multiple constructor overloads.
 */
data class Book(
    val id: String,
    val title: String,
    val authors: List<String>,
    val description: String,
    val thumbnailUrl: String?,
    val publishedDate: String?
)
