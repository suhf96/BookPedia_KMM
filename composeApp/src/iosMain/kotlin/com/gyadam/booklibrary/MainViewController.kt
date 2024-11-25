package com.gyadam.booklibrary

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.gyadam.booklibrary.di.initKoin
import io.ktor.client.engine.darwin.Darwin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}