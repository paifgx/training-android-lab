package dev.training.bookshelf.local

import dev.training.bookshelf.model.Book

/**
 * Mappers between Room Entity and domain model.
 *
 * Tips:
 * - Two-way mapping: Entity → Domain and Domain → Entity.
 * - The Entity has extra fields (lastSearchQuery, cachedAt) for caching metadata.
 * - The domain Book knows nothing about caching — clean separation.
 * - In a GreenDao migration, you'd write similar mappers between GreenDao entities and domain.
 */

fun BookEntity.toDomainModel(): Book = Book(
    id = id,
    title = title,
    authors = authors,
    description = description,
    thumbnailUrl = thumbnailUrl,
    publishedDate = publishedDate
)

fun Book.toEntity(query: String): BookEntity = BookEntity(
    id = id,
    title = title,
    authors = authors,
    description = description,
    thumbnailUrl = thumbnailUrl,
    publishedDate = publishedDate,
    lastSearchQuery = query,
    cachedAt = System.currentTimeMillis()
)

fun List<BookEntity>.toDomainModels(): List<Book> = map { it.toDomainModel() }

fun List<Book>.toEntities(query: String): List<BookEntity> = map { it.toEntity(query) }
