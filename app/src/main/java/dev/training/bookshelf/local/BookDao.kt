package dev.training.bookshelf.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Data Access Object — defines SQL operations on the books table.
 *
 * Tips:
 * - @Dao marks this as a Room DAO. Room generates the implementation.
 * - @Insert, @Query, @Update, @Delete — declarative SQL, no implementation needed.
 * - OnConflictStrategy.REPLACE: if a book with the same ID exists, overwrite it.
 * - Return types matter:
 *   - Flow<List<...>> = observe changes in real-time
 *   - suspend fun = one-shot operation (insert, delete)
 *   - In Java, you'd use LiveData or RxJava. Room supports both, but Flow is Kotlin-first.
 */
@Dao
interface BookDao {

    @Query("SELECT * FROM books WHERE lastSearchQuery LIKE '%' || :query || '%' ORDER BY cachedAt DESC")
    fun getBooksByQuery(query: String): kotlinx.coroutines.flow.Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE id = :id")
    fun getBookById(id: String): kotlinx.coroutines.flow.Flow<BookEntity?>

    @Query("SELECT * FROM books ORDER BY cachedAt DESC")
    fun getAllBooks(): kotlinx.coroutines.flow.Flow<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<BookEntity>)

    @Query("DELETE FROM books WHERE lastSearchQuery LIKE '%' || :query || '%'")
    suspend fun deleteByQuery(query: String)

    @Query("DELETE FROM books")
    suspend fun deleteAll()
}
