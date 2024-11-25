package com.gyadam.booklibrary

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.gyadam.booklibrary.bookLibrary.data.network.KtorRemoteBookDataSource
import com.gyadam.booklibrary.bookLibrary.data.network.RemoteBookDatasource
import com.gyadam.booklibrary.bookLibrary.data.repository.DefaultBookRepository
import com.gyadam.booklibrary.bookLibrary.presentation.book_list.BookListScreenRoot
import com.gyadam.booklibrary.bookLibrary.presentation.book_list.BookListViewModel
import com.gyadam.booklibrary.core.data.HttpClientFactory
import io.ktor.client.engine.HttpClientEngine
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
@Preview
fun App() {
    val viewModel = koinViewModel<BookListViewModel>()
    MaterialTheme {
        BookListScreenRoot(
            viewModel = viewModel,
            onBookClick = { },
        )
    }
}