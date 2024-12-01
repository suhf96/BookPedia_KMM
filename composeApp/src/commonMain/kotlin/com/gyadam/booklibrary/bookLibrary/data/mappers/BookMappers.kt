package com.gyadam.booklibrary.bookLibrary.data.mappers

import com.gyadam.booklibrary.bookLibrary.data.database.BookEntity
import com.gyadam.booklibrary.bookLibrary.data.dto.BookDTO
import com.gyadam.booklibrary.bookLibrary.domain.Book

fun BookDTO.toBook(): Book {
    return Book(
        id = id.substringAfterLast("/"),
        title = title,
        imageUrl = if (coverKey != null) {
            "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
        } else {
            "https://covers.openlibrary.org/b/id/${coverAlternativeKey}-L.jpg"
        },
        authors = authorNames ?: emptyList(),
        description = null,
        languages = languages ?: emptyList(),
        firstPublishYear = firstPublishYear.toString(),
        averageRating = ratingsAverage,
        ratingCount = ratingsCount,
        numPages = numPagesMedian,
        numEditions = numEditions ?: 0
    )
}

fun Book.toBookEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title,
        authors = authors,
        description = description,
        imageUrl = imageUrl,
        languages = languages,
        firstPublishYear = firstPublishYear,
        ratingsAverage = averageRating,
        ratingsCount = ratingCount,
        numPagesMedian = numPages,
        numberOfEdition = numEditions
    )
}


fun BookEntity.toBook(): Book {
    return Book(
        id = id,
        title = title,
        authors = authors,
        description = description,
        imageUrl = imageUrl,
        languages = languages,
        firstPublishYear = firstPublishYear,
        averageRating = ratingsAverage,
        ratingCount = ratingsCount,
        numPages = numPagesMedian,
        numEditions = numberOfEdition ?: 0,
    )
}
