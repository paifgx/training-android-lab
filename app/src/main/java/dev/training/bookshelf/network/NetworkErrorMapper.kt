package dev.training.bookshelf.network

import dev.training.bookshelf.model.NetworkError

/**
 * Extension to convert Retrofit exceptions to typed NetworkError.
 */
fun Exception.toNetworkError(): NetworkError = when (this) {
    is java.net.UnknownHostException -> NetworkError.NetworkUnavailable(this)
    is com.squareup.moshi.JsonDataException -> NetworkError.ParsingError(this)
    is retrofit2.HttpException -> NetworkError.HttpError(code(), message())
    else -> NetworkError.Unknown
}
