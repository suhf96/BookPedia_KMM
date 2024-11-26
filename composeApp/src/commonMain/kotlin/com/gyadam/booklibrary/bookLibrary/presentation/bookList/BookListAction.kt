package com.gyadam.booklibrary.bookLibrary.presentation.bookList

import com.gyadam.booklibrary.bookLibrary.domain.Book

sealed interface BookListAction {
    data class OnSearchQueryChange(val query: String) : BookListAction
    data class OnBookClick(val book: Book) : BookListAction
    data class OnTabSelected(val index: Int) : BookListAction
}