package com.gyadam.booklibrary

import androidx.compose.ui.window.ComposeUIViewController
import com.gyadam.booklibrary.bookLibrary.app.App
import com.gyadam.booklibrary.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}