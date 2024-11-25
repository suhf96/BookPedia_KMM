package com.gyadam.booklibrary.bookLibrary.presentation.book_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import booklibrary.composeapp.generated.resources.Res
import booklibrary.composeapp.generated.resources.favourites_tab
import booklibrary.composeapp.generated.resources.no_favourites
import booklibrary.composeapp.generated.resources.no_result
import booklibrary.composeapp.generated.resources.search_result_tab
import com.gyadam.booklibrary.bookLibrary.domain.Book
import com.gyadam.booklibrary.bookLibrary.presentation.book_list.components.BookList
import com.gyadam.booklibrary.bookLibrary.presentation.book_list.components.BookSearchBar
import com.gyadam.booklibrary.core.presentation.DarkBlue
import com.gyadam.booklibrary.core.presentation.DesertWhite
import com.gyadam.booklibrary.core.presentation.SandYellow
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun BookListScreenRoot(
    viewModel: BookListViewModel = koinViewModel(),
    onBookClick: (book: Book) -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BookListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is BookListAction.OnBookClick -> onBookClick(action.book)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun BookListScreen(
    state: BookListState,
    onAction: (BookListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val pagerState = rememberPagerState {
        2
    }
    val searchResultsListState = rememberLazyListState()
    val favouritesListState = rememberLazyListState()
    LaunchedEffect(key1 = state.searchResults) {
        searchResultsListState.animateScrollToItem(0)
    }
    LaunchedEffect(key1 = state.selectedTabIndex) {
        pagerState.animateScrollToPage(state.selectedTabIndex)

    }
    LaunchedEffect(key1 = pagerState.currentPage) {
        if (pagerState.isScrollInProgress) {
            onAction(BookListAction.OnTabSelected(pagerState.currentPage))
        }
    }

    Column(
        modifier = modifier
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
        Surface(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            color = DesertWhite,
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TabRow(
                    selectedTabIndex = state.selectedTabIndex,
                    modifier = Modifier.padding(vertical = 12.dp).widthIn(max = 700.dp)
                        .fillMaxWidth(),
                    indicator = { tabPosition ->
                        TabRowDefaults.SecondaryIndicator(
                            color = SandYellow,
                            modifier = Modifier.tabIndicatorOffset(
                                tabPosition[state.selectedTabIndex]
                            )
                        )
                    }
                ) {
                    Tab(
                        selected = state.selectedTabIndex == 0,
                        onClick = {
                            onAction(BookListAction.OnTabSelected(0))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = stringResource(Res.string.search_result_tab),
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }
                    Tab(
                        selected = state.selectedTabIndex == 1,
                        onClick = {
                            onAction(BookListAction.OnTabSelected(1))

                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = stringResource(Res.string.favourites_tab),
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth().weight(1f)
                ) { pageIndex ->
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        when (pageIndex) {
                            0 -> {
                                if (state.isLoading) {
                                    CircularProgressIndicator()
                                } else {
                                    when {
                                        state.errorMessage != null -> Text(
                                            text = state.errorMessage.asString(),
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.headlineSmall,
                                            color = MaterialTheme.colorScheme.error
                                        )

                                        state.searchResults.isEmpty() -> {
                                            Text(
                                                text = stringResource(Res.string.no_result),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.headlineLarge,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                        }

                                        else -> {
                                            BookList(
                                                books = state.searchResults,
                                                onBookClick = {
                                                    onAction(BookListAction.OnBookClick(it))
                                                },
                                                modifier = Modifier.fillMaxSize(),
                                                scrollState = searchResultsListState
                                            )
                                        }
                                    }
                                }
                            }

                            1 -> {
                                if (state.favouriteBooks.isEmpty()) {
                                    Text(
                                        text = stringResource(Res.string.no_favourites),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.headlineLarge,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                } else {
                                    BookList(
                                        books = state.favouriteBooks,
                                        onBookClick = {
                                            onAction(BookListAction.OnBookClick(it))
                                        },
                                        modifier = Modifier.fillMaxSize(),
                                        scrollState = favouritesListState
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}

