package com.gyadam.booklibrary.bookLibrary.presentation.bookDetails

import com.gyadam.booklibrary.bookLibrary.domain.Book

sealed interface BookDetailAction {
    data object OnBackClick : BookDetailAction
    data object OnFavouriteClick : BookDetailAction
    data class OnSelectedBookChange(val book: Book) : BookDetailAction
}