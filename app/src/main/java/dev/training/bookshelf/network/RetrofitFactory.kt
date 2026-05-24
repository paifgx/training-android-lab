package dev.training.bookshelf.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.training.bookshelf.model.NetworkError
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Creates and configures the Retrofit instance.
 *
 * This will later become a Hilt module (task/06).
 */
object RetrofitFactory {

    private const val BASE_URL = "https://openlibrary.org/"

    fun createApiService(): OpenLibraryApiService {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(OpenLibraryApiService::class.java)
    }
}

fun Exception.toNetworkError(): NetworkError = when (this) {
    is java.net.UnknownHostException -> NetworkError.NetworkUnavailable(this)
    is com.squareup.moshi.JsonDataException -> NetworkError.ParsingError(this)
    is retrofit2.HttpException -> NetworkError.HttpError(code(), message())
    else -> NetworkError.Unknown
}
