package com.gyadam.booklibrary.bookLibrary.presentation.bookDetails

import com.gyadam.booklibrary.bookLibrary.domain.Book

data class BookDetailState(
    val isLoading:Boolean = true,
    val isFavourite : Boolean = false,
    val book: Book? = null,
)
