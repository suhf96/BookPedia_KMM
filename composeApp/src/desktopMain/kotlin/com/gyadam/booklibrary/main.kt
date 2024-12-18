package com.gyadam.booklibrary

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.gyadam.booklibrary.bookLibrary.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "BookLibrary",
    ) {
        App()
    }
}