package dev.training.bookshelf.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.training.bookshelf.local.BookDao
import dev.training.bookshelf.local.BookDatabase
import javax.inject.Singleton

/**
 * Hilt module that provides Room database and DAO.
 *
 * @Module = tells Hilt this is a dependency provider
 * @InstallIn(SingletonComponent::class) = lives as long as the Application
 * @Singleton = only one instance across the entire app
 *
 * Tips:
 * - In Java/Dagger, this is the same concept but with more boilerplate.
 * - @Provides = "here's how to create this dependency"
 * - @ApplicationContext = Hilt injects the application context (not activity context)
 * - SingletonComponent = application-scoped (vs ActivityComponent = per-activity)
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BookDatabase =
        Room.databaseBuilder(
            context,
            BookDatabase::class.java,
            "bookshelf.db"
        ).build()

    @Provides
    fun provideBookDao(database: BookDatabase): BookDao =
        database.bookDao()
}
