package com.gyadam.booklibrary.bookLibrary.domain.repository

import com.gyadam.booklibrary.bookLibrary.domain.Book
import com.gyadam.booklibrary.core.domain.DataError
import com.gyadam.booklibrary.core.domain.EmptyResult
import com.gyadam.booklibrary.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>

    suspend fun getBookDescription(bookId: String): Result<String?, DataError>

    fun getFavouriteBooks(): Flow<List<Book>>

    fun isBookFavourite(bookId: String): Flow<Boolean>

    suspend fun markAsFavourite(book: Book) : EmptyResult<DataError.Local>
    suspend fun deleteFromFavourites(bookId:String)

}

