package com.gyadam.booklibrary.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.gyadam.booklibrary.bookLibrary.data.database.DatabaseFactory
import com.gyadam.booklibrary.bookLibrary.data.database.FavouriteBookDatabase
import com.gyadam.booklibrary.bookLibrary.data.network.KtorRemoteBookDataSource
import com.gyadam.booklibrary.bookLibrary.data.repository.DefaultBookRepository
import com.gyadam.booklibrary.bookLibrary.data.network.RemoteBookDatasource
import com.gyadam.booklibrary.bookLibrary.domain.repository.BookRepository
import com.gyadam.booklibrary.bookLibrary.presentation.bookList.BookListViewModel
import com.gyadam.booklibrary.bookLibrary.presentation.bookDetails.BookDetailViewModel
import com.gyadam.booklibrary.bookLibrary.presentation.SelectedBookViewModel
import com.gyadam.booklibrary.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    single {
        HttpClientFactory.create(get())
    }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDatasource>()

    singleOf(::DefaultBookRepository).bind<BookRepository>()


    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single {
        get<FavouriteBookDatabase>().dao
    }
    viewModelOf(::BookListViewModel)
    viewModelOf(::SelectedBookViewModel)
    viewModelOf(::BookDetailViewModel)
}