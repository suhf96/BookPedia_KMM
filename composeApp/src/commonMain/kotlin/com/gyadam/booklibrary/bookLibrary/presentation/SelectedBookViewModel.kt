package com.gyadam.booklibrary.bookLibrary.presentation

import androidx.lifecycle.ViewModel
import com.gyadam.booklibrary.bookLibrary.domain.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SelectedBookViewModel : ViewModel() {
    private val _selectedBook = MutableStateFlow<Book?>(null)
    val selectedBook: StateFlow<Book?> = _selectedBook

    fun onSelectBook(book: Book?) {
        _selectedBook.value = book
    }
}