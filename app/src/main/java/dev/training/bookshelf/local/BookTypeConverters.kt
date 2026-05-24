package dev.training.bookshelf.local

import androidx.room.TypeConverter

/**
 * Type converters for Room — teaches Room how to store types it doesn't natively support.
 *
 * Tips:
 * - Room natively supports: primitives, String, ByteArray.
 * - For everything else (List, custom types), you write a TypeConverter.
 * - Here we convert List<String> ↔ String using JSON array format.
 * - In a GreenDao world, you'd store this as a concatenated string or a separate table.
 *   Room's TypeConverters are cleaner — one annotation and you're done.
 *
 * Advanced: For complex objects, consider using Moshi/Gson as the converter.
 */
class BookTypeConverters {

    @TypeConverter
    fun fromStringList(value: List<String>): String =
        value.joinToString(separator = "§§", prefix = "[", postfix = "]")

    @TypeConverter
    fun toStringList(value: String): List<String> {
        if (value.isEmpty()) return emptyList()
        return value
            .removeSurrounding("[", "]")
            .split("§§")
            .filter { it.isNotBlank() }
    }
}
