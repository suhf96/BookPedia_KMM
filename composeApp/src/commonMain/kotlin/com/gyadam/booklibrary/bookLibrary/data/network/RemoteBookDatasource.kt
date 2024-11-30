package com.gyadam.booklibrary.bookLibrary.data.network

import androidx.room.Query
import com.gyadam.booklibrary.bookLibrary.data.dto.BookWorkDTO
import com.gyadam.booklibrary.bookLibrary.data.dto.SearchResultsDTO
import com.gyadam.booklibrary.core.domain.DataError
import com.gyadam.booklibrary.core.domain.Result

interface RemoteBookDatasource {

    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResultsDTO, DataError.Remote>

    suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDTO, DataError.Remote>
}