package dev.training.bookshelf.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room Database — the central access point for the SQLite database.
 *
 * Tips:
 * - @Database marks this as a Room database. entities = list of all tables.
 * - version = 1 → will need migration when the schema changes (task/04 covers this).
 * - exportSchema = false avoids schema-location setup for this training lab. In production migrations, enable schema export.
 * - The singleton pattern (INSTANCE) prevents opening multiple database connections.
 * - In GreenDao: DaoSession + DaoMaster. In Room: RoomDatabase + @Dao.
 *   Room is annotation-processor-based (KSP), not code-generation-based.
 */
@Database(
    entities = [BookEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getInstance(context: Context): BookDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    "bookshelf.db"
                ).build().also { INSTANCE = it }
            }
    }
}
