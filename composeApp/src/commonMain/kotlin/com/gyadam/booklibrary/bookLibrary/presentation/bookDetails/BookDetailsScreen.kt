package com.gyadam.booklibrary.bookLibrary.presentation.bookDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import booklibrary.composeapp.generated.resources.Res
import booklibrary.composeapp.generated.resources.book_description_unavailable
import booklibrary.composeapp.generated.resources.book_languages_descp
import booklibrary.composeapp.generated.resources.book_pages_descp
import booklibrary.composeapp.generated.resources.book_rating_descp
import booklibrary.composeapp.generated.resources.book_synopsis_descp
import com.gyadam.booklibrary.bookLibrary.domain.Book
import com.gyadam.booklibrary.bookLibrary.presentation.bookDetails.components.BlurredImageBackground
import com.gyadam.booklibrary.bookLibrary.presentation.bookDetails.components.BookChip
import com.gyadam.booklibrary.bookLibrary.presentation.bookDetails.components.ChipSize
import com.gyadam.booklibrary.bookLibrary.presentation.bookDetails.components.TitledContent
import com.gyadam.booklibrary.core.presentation.SandYellow
import org.jetbrains.compose.resources.stringResource
import kotlin.math.round

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
    ) {
        if (state.book != null)
            BookDetails(
                book = state.book,
                isLoading = state.isLoading,
                modifier = Modifier.fillMaxSize()
            )

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BookDetails(
    book: Book,
    isLoading: Boolean,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .widthIn(700.dp)
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 24.dp)
            .verticalScroll(
                rememberScrollState(),
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = book.title,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        Text(
            text = book.authors.joinToString(),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            book.averageRating?.let { rating ->
                TitledContent(
                    title = stringResource(Res.string.book_rating_descp),
                ) {
                    BookChip {
                        Text(
                            text = "${round(rating * 10) / 10.0}"
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = SandYellow
                        )
                    }
                }
            }

            book.numPages?.let { pageCount ->
                TitledContent(
                    title = stringResource(Res.string.book_pages_descp),
                ) {
                    BookChip {
                        Text(
                            text = pageCount.toString()
                        )
                    }
                }
            }
        }

        if (book.languages.isNotEmpty()) {
            TitledContent(
                title = stringResource(Res.string.book_languages_descp),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                FlowRow(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.wrapContentSize(Alignment.Center)
                ) {
                    book.languages.forEach { language ->
                        BookChip(
                            size = ChipSize.SMALL,
                            modifier = Modifier.padding(2.dp)
                        ) {
                            Text(
                                text = language.uppercase(),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
        Text(
            text = stringResource(Res.string.book_synopsis_descp),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.Start).fillMaxWidth()
                .padding(top = 24.dp, bottom = 8.dp)
        )
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth().weight(1f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {

            Text(
                text = if (!book.description.isNullOrBlank())
                    book.description else
                    stringResource(Res.string.book_description_unavailable),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                color = if (book.description.isNullOrBlank()) Color.Black.copy(alpha = 0.4f) else Color.Black,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
        }
    }
}
