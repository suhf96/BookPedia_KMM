package com.gyadam.booklibrary.bookLibrary.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResultsDTO(
    @SerialName("docs") val results: List<BookDTO>

)
