package dev.training.bookshelf.model

sealed interface NetworkError {
    data class HttpError(val code: Int, val message: String) : NetworkError
    data class NetworkUnavailable(val cause: Throwable) : NetworkError
    data class ParsingError(val cause: Throwable) : NetworkError
    data object Unknown : NetworkError
}

fun NetworkError.toUserMessage(): String = when (this) {
    is NetworkError.HttpError -> "Server error $code: $message"
    is NetworkError.NetworkUnavailable -> "Network unavailable. Showing cached or fallback data if available."
    is NetworkError.ParsingError -> "The server response could not be read."
    NetworkError.Unknown -> "An unknown error occurred."
}
