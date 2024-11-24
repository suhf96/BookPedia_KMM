package com.gyadam.booklibrary.bookLibrary.data.network

import com.gyadam.booklibrary.bookLibrary.data.dto.SearchResultsDTO
import com.gyadam.booklibrary.bookLibrary.domain.Book
import com.gyadam.booklibrary.core.data.safeCall
import com.gyadam.booklibrary.core.domain.DataError
import com.gyadam.booklibrary.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://openlibrary.org"
private const val FIELDS = "key,title,language,cover_i,author_key,author_name," +
        "cover_edition_key,first_publish_year,ratings_average,ratings_count," +
        "number_of_pages_median,edition_count"

class KtorRemoteBookDataSource(
    private val httpClient: HttpClient,
) : RemoteBookDatasource {
    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): Result<SearchResultsDTO, DataError.Remote> = safeCall {
        httpClient.get(
            urlString = "$BASE_URL/search.json"
        ) {
            parameter("q", query)
            parameter("limit", resultLimit)
            parameter("language", "eng")
            parameter("fields", FIELDS)
        }
    }
}