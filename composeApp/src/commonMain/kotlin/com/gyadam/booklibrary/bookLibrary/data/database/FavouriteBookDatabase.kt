package com.gyadam.booklibrary.bookLibrary.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [BookEntity::class],
    version = 1
)
@TypeConverters(StringListTypeConverter::class)
abstract class FavouriteBookDatabase : RoomDatabase() {
    abstract val dao: FavouriteBookDao

    companion object {
        const val DATABASE_NAME = "book.db"
    }
}