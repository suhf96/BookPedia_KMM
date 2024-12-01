package com.gyadam.booklibrary.bookLibrary.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val authors: List<String>,
    val description: String?,
    val imageUrl: String,
    val languages: List<String>,
    val firstPublishYear: String?,
    val ratingsAverage: Double?,
    val ratingsCount: Int?,
    val numPagesMedian: Int?,
    val numberOfEdition: Int?
)