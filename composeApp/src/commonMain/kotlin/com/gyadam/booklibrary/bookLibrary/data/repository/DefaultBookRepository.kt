package com.gyadam.booklibrary.bookLibrary.data.repository

import com.gyadam.booklibrary.bookLibrary.data.mappers.toBook
import com.gyadam.booklibrary.bookLibrary.data.network.RemoteBookDatasource
import com.gyadam.booklibrary.bookLibrary.domain.Book
import com.gyadam.booklibrary.bookLibrary.domain.repository.BookRepository
import com.gyadam.booklibrary.core.domain.DataError
import com.gyadam.booklibrary.core.domain.Result
import com.gyadam.booklibrary.core.domain.map

class DefaultBookRepository(
    private val remoteBookDatasource: RemoteBookDatasource,
) : BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDatasource
            .searchBooks(query)
            .map { books ->
                books.results.map { it.toBook() }
            }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        return remoteBookDatasource
            .getBookDetails(bookId)
            .map { bookDetails ->
                bookDetails.description
            }
    }
}