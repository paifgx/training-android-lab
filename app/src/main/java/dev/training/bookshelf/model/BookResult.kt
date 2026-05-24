package dev.training.bookshelf.model

sealed interface BookResult<out T> {
    data class Success<T>(val data: T) : BookResult<T>
    data class Failure(val error: NetworkError) : BookResult<Nothing>
}
