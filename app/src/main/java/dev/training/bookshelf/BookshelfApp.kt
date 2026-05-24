package dev.training.bookshelf

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class annotated with @HiltAndroidApp.
 *
 * This triggers Hilt's code generation and creates the application-level
 * dependency container. Without this annotation, Hilt won't work at all.
 *
 * Must also be registered in AndroidManifest.xml via android:name.
 */
@HiltAndroidApp
class BookshelfApp : Application()
