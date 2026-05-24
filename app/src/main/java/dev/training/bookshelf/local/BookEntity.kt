package dev.training.bookshelf.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

/**
 * Room Entity — one row in the "books" table.
 *
 * Tips:
 * - @Entity tells Room this class maps to a database table.
 * - @PrimaryKey marks the unique identifier.
 * - In Java, you'd need getters/setters. Room with Kotlin works with val properties.
 * - authors is a List<String> — Room can't store lists natively.
 *   That's why we need the BookTypeConverters class.
 * - This is the DATABASE model, separate from the domain Book and the network DTO.
 *   Three layers of models: Network (DTO) ↔ Database (Entity) ↔ Domain (Book).
 */
@Entity(tableName = "books")
@TypeConverters(BookTypeConverters::class)
data class BookEntity(
    @PrimaryKey val id: String,
    val title: String,
    val authors: List<String>,
    val description: String,
    val thumbnailUrl: String?,
    val publishedDate: String?,
    val lastSearchQuery: String,
    val cachedAt: Long = System.currentTimeMillis()
)
