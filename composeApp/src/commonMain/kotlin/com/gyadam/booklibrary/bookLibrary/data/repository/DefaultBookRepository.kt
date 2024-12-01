package com.gyadam.booklibrary.bookLibrary.data.repository

import androidx.sqlite.SQLiteException
import com.gyadam.booklibrary.bookLibrary.data.database.FavouriteBookDao
import com.gyadam.booklibrary.bookLibrary.data.mappers.toBook
import com.gyadam.booklibrary.bookLibrary.data.mappers.toBookEntity
import com.gyadam.booklibrary.bookLibrary.data.network.RemoteBookDatasource
import com.gyadam.booklibrary.bookLibrary.domain.Book
import com.gyadam.booklibrary.bookLibrary.domain.repository.BookRepository
import com.gyadam.booklibrary.core.domain.DataError
import com.gyadam.booklibrary.core.domain.EmptyResult
import com.gyadam.booklibrary.core.domain.Result
import com.gyadam.booklibrary.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultBookRepository(
    private val remoteBookDatasource: RemoteBookDatasource,
    private val favouriteBookDao: FavouriteBookDao
) : BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDatasource
            .searchBooks(query)
            .map { books ->
                books.results.map { it.toBook() }
            }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        val localResult = favouriteBookDao.getFavouriteBook(bookId)

        return if (localResult == null) remoteBookDatasource
            .getBookDetails(bookId)
            .map { bookDetails ->
                bookDetails.description
            } else Result.Success(localResult.description)
    }

    override fun getFavouriteBooks(): Flow<List<Book>> =
        favouriteBookDao.getFavouriteBooks().map { entities ->
            entities.map { it.toBook() }

        }


    override fun isBookFavourite(bookId: String): Flow<Boolean> {
        return favouriteBookDao.getFavouriteBooks().map { entities ->
            entities.any { it.id == bookId }
        }
    }

    override suspend fun markAsFavourite(book: Book): EmptyResult<DataError.Local> = try {
        favouriteBookDao.upsert(book.toBookEntity())
        Result.Success(Unit)
    } catch (e: SQLiteException) {
        Result.Error(DataError.Local.DISK_FULL)
    }


    override suspend fun deleteFromFavourites(bookId: String) =
        favouriteBookDao.deleteFavouriteBook(bookId)

}