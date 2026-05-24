package dev.training.bookshelf.model

@JvmInline
value class SearchQuery private constructor(val value: String) {
    val isBlank: Boolean get() = value.isBlank()

    companion object {
        fun from(raw: String): SearchQuery = SearchQuery(raw.trim())
    }
}
