package dev.training.bookshelf.local

fun BookEntity.cacheAgeMillis(now: Long = System.currentTimeMillis()): Long =
    (now - cachedAt).coerceAtLeast(0L)

fun BookEntity.isOlderThan(maxAgeMillis: Long, now: Long = System.currentTimeMillis()): Boolean =
    cacheAgeMillis(now) > maxAgeMillis
