package dev.training.bookshelf.model

sealed interface NetworkError {
    data class HttpError(val code: Int, val message: String) : NetworkError
    data class NetworkUnavailable(val cause: Throwable) : NetworkError
    data class ParsingError(val cause: Throwable) : NetworkError
    data object Unknown : NetworkError
}
