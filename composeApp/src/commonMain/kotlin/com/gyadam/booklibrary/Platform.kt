package com.gyadam.booklibrary

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform