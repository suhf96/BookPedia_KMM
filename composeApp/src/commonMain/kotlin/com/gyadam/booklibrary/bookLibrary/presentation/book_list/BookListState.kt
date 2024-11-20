package com.gyadam.booklibrary.bookLibrary.presentation.book_list

import com.gyadam.booklibrary.bookLibrary.domain.Book
import com.gyadam.booklibrary.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = books,
    val favouriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null,
)

val books = listOf(
    Book(
        id = "1",
        title = "1984",
        authors = listOf("George Orwell"),
        description = "A dystopian novel set in a totalitarian society under constant surveillance.",
        imageUrl = "https://example.com/1984.jpg",
        languages = listOf("English"),
        firstPublishYear = "1949",
        averageRating = 4.17,
        ratingCount = 3100000,
        numPages = 328,
        numEditions = 200
    ),
    Book(
        id = "2",
        title = "To Kill a Mockingbird",
        authors = listOf("Harper Lee"),
        description = "A novel about racial injustice in the Deep South.",
        imageUrl = "https://example.com/to-kill-a-mockingbird.jpg",
        languages = listOf("English"),
        firstPublishYear = "1960",
        averageRating = 4.27,
        ratingCount = 4500000,
        numPages = 281,
        numEditions = 150
    ),
    Book(
        id = "3",
        title = "The Great Gatsby",
        authors = listOf("F. Scott Fitzgerald"),
        description = "A critique of the American Dream set in the Roaring Twenties.",
        imageUrl = "https://example.com/the-great-gatsby.jpg",
        languages = listOf("English"),
        firstPublishYear = "1925",
        averageRating = 3.91,
        ratingCount = 3900000,
        numPages = 180,
        numEditions = 120
    ),
    Book(
        id = "4",
        title = "Pride and Prejudice",
        authors = listOf("Jane Austen"),
        description = "A romantic novel exploring the themes of love, class, and societal expectations.",
        imageUrl = "https://example.com/pride-and-prejudice.jpg",
        languages = listOf("English"),
        firstPublishYear = "1813",
        averageRating = 4.25,
        ratingCount = 2800000,
        numPages = 279,
        numEditions = 95
    ),
    Book(
        id = "5",
        title = "Moby-Dick",
        authors = listOf("Herman Melville"),
        description = "An epic tale of obsession and revenge on the high seas.",
        imageUrl = "https://example.com/moby-dick.jpg",
        languages = listOf("English"),
        firstPublishYear = "1851",
        averageRating = 3.49,
        ratingCount = 480000,
        numPages = 635,
        numEditions = 75
    )
)
