package com.gyadam.booklibrary.bookLibrary.presentation.book_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gyadam.booklibrary.bookLibrary.presentation.book_list.components.BookSearchBar
import com.gyadam.booklibrary.core.presentation.DarkBlue
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun BookListScreenRoot(
    viewModel: BookListViewModel = koinViewModel(),
    onBookClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    BookListScreen(
        state = state,
        onBookClick = { onBookClick() },
        onAction = viewModel::onAction
    )
}

@Composable
fun BookListScreen(
    state: BookListState,
    onAction: (BookListAction) -> Unit,
    onBookClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BookSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = { onAction(BookListAction.OnSearchQueryChange(it)) },
            onImeSearchAction = {
                keyboardController?.hide()
            },
            modifier = Modifier.widthIn(max = 500.dp).fillMaxWidth().padding(16.dp)

        )
    }


}

