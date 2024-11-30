package com.gyadam.booklibrary.bookLibrary.data.dto

import kotlinx.serialization.Serializable

@Serializable(with = BookWorkDtoSerializer::class)
data class BookWorkDTO(
    val description: String? = null
)
