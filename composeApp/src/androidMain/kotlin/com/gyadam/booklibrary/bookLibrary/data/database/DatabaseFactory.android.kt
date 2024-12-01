package com.gyadam.booklibrary.bookLibrary.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<FavouriteBookDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(FavouriteBookDatabase.DATABASE_NAME)
        return Room.databaseBuilder<FavouriteBookDatabase>(
            name = dbFile.absolutePath,
            context = appContext
        )
    }
}