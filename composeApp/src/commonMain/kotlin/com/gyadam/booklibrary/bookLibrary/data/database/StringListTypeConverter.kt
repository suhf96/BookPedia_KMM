package com.gyadam.booklibrary.bookLibrary.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object StringListTypeConverter {

    @TypeConverter
    fun fromString(value :String) :List<String>{
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun toString(list :List<String>) :String{
        return Json.encodeToString(list)
    }
}