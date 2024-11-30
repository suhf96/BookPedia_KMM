package com.gyadam.booklibrary.bookLibrary.presentation.bookList

import com.gyadam.booklibrary.bookLibrary.domain.Book
import com.gyadam.booklibrary.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = emptyList(),
    val favouriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null,
)
