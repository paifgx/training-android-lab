package dev.training.bookshelf.data

data class CachePolicy(
    val maxAgeMillis: Long = 15 * 60 * 1000L
) {
    fun isStale(cacheAgeMillis: Long): Boolean = cacheAgeMillis > maxAgeMillis
}
