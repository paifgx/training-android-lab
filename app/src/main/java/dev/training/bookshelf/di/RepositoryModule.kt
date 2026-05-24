package dev.training.bookshelf.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.training.bookshelf.data.BookRepository
import dev.training.bookshelf.data.DefaultBookRepository
import javax.inject.Singleton

/**
 * Hilt module that binds the BookRepository interface to its implementation.
 *
 * @Binds vs @Provides:
 * - @Provides: "here's a function that creates the dependency" (used in NetworkModule/DatabaseModule)
 * - @Binds: "when someone asks for X, give them this implementation Y"
 *
 * Why @Binds here?
 * - We have an interface (BookRepository) and an implementation (DefaultBookRepository).
 * - @Binds tells Hilt: "whenever a class asks for BookRepository, inject DefaultBookRepository."
 * - This is the same as writing `@Provides fun provideRepo(): BookRepository = DefaultBookRepository(...)`
 *   but cleaner — Hilt knows how to create DefaultBookRepository from the other modules.
 *
 * Must be an abstract class (not object) when using @Binds.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBookRepository(impl: DefaultBookRepository): BookRepository
}
