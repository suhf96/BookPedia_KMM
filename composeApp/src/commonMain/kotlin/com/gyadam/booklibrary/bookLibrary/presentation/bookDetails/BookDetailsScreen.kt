package com.gyadam.booklibrary.bookLibrary.presentation.bookDetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gyadam.booklibrary.bookLibrary.presentation.bookDetails.components.BlurredImageBackground

@Composable
fun BookDetailScreenRoot(
    viewModel: BookDetailViewModel,
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    BookDetailScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is BookDetailAction.OnBackClick -> onBackClick()
                else -> {
                    println("Other")
                }
            }
            viewModel.onAction(action)
        }
    )

}

@Composable
private fun BookDetailScreen(
    state: BookDetailState,
    onAction: (BookDetailAction) -> Unit,
    modifier: Modifier = Modifier
) {

        BlurredImageBackground(
            imageUrl = state.book?.imageUrl ?: "",
            isFavourite = state.isFavourite,
            onFavouriteClick = {
                onAction(BookDetailAction.OnFavouriteClick)
            },
            onBackClick = {
                onAction(BookDetailAction.OnBackClick)
            },
            modifier = modifier.fillMaxSize()
        ){

        }
}