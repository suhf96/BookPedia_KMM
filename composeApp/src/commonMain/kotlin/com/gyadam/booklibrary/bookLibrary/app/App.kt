package com.gyadam.booklibrary.bookLibrary.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.gyadam.booklibrary.bookLibrary.presentation.book_list.BookListScreenRoot
import com.gyadam.booklibrary.bookLibrary.presentation.book_list.BookListViewModel
import com.gyadam.booklibrary.bookLibrary.presentation.book_list.SelectedBookViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
@Preview
fun App() {
    val viewModel = koinViewModel<BookListViewModel>()

    val navController = rememberNavController()
    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = Routes.BookGraph
        ) {
            navigation<Routes.BookGraph>(
                startDestination = Routes.BookList
            ) {

                composable<Routes.BookList> {
                    val selectedBookViewModel =
                        it.sharedKoinViewModel<SelectedBookViewModel>(navController)

                    LaunchedEffect(true) {
                        selectedBookViewModel.onSelectBook(null)
                    }
                    BookListScreenRoot(
                        viewModel = viewModel,
                        onBookClick = { book ->
                            selectedBookViewModel.onSelectBook(book)
                            navController.navigate(Routes.BookDetail(book.id))
                        },
                    )
                }
                composable<Routes.BookDetail> { entry ->
                    val selectedBookViewModel =
                        entry.sharedKoinViewModel<SelectedBookViewModel>(navController)
                    val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Book ID ${selectedBook?.title}")
                    }
                }
            }
        }
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}