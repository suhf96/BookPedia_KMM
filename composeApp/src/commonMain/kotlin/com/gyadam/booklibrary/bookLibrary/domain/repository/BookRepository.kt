package com.gyadam.booklibrary.bookLibrary.domain.repository

import com.gyadam.booklibrary.bookLibrary.domain.Book
import com.gyadam.booklibrary.core.domain.DataError
import com.gyadam.booklibrary.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>

    suspend fun getBookDescription(bookId: String): Result<String?, DataError>
}