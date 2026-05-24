package de.gfu.training.bookshelf.util

fun String?.isNotNullOrBlank(): Boolean = !this.isNullOrBlank()

fun String.truncate(maxLength: Int): String =
    if (this.length <= maxLength) this
    else this.take(maxLength - 1) + "…"

fun List<String>.formatAuthors(): String =
    when {
        isEmpty() -> "Unknown Author"
        size == 1 -> first()
        size <= 3 -> joinToString(", ")
        else -> take(3).joinToString(", ") + " et al."
    }
