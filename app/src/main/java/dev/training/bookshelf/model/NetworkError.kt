package dev.training.bookshelf.model

/**
 * Sealed hierarchy for network/API errors.
 *
 * Tips:
 * - Instead of throwing exceptions or returning null, Kotlin favors explicit error types.
 * - This sealed interface lets the caller handle each error case explicitly.
 * - Think of it as a type-safe replacement for checked exceptions (which Kotlin doesn't have).
 */
sealed interface NetworkError {
    data class HttpError(val code: Int, val message: String) : NetworkError
    data class NetworkUnavailable(val cause: Throwable) : NetworkError
    data class ParsingError(val cause: Throwable) : NetworkError
    data object Unknown : NetworkError
}
