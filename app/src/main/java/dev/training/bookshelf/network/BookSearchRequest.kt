package dev.training.bookshelf.network

data class BookSearchRequest(
    val query: String,
    val limit: Int = 20,
    val fields: String = OpenLibraryApiService.DEFAULT_FIELDS
) {
    init {
        require(limit in 1..100) { "limit must be between 1 and 100" }
    }

    val normalizedQuery: String = query.trim()
}
