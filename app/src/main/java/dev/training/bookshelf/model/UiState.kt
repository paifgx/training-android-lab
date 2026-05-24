package dev.training.bookshelf.model

/**
 * Sealed hierarchy representing the UI state of a screen.
 *
 * Tips:
 * - In Java you might use an enum + separate data holder, or a tagged union pattern.
 * - Kotlin's `sealed class` (or `sealed interface`) gives you exhaustive when-expressions
 *   and type-safe state handling — the compiler tells you if you miss a case.
 * - `object` = singleton. Use it for states that carry no data.
 * - `data class` = states that carry data.
 */
sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}
