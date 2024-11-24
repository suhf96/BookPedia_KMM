package com.gyadam.booklibrary.core.presentation

import booklibrary.composeapp.generated.resources.Res
import booklibrary.composeapp.generated.resources.error_disk_full
import booklibrary.composeapp.generated.resources.error_local_unknown
import booklibrary.composeapp.generated.resources.error_no_internet
import booklibrary.composeapp.generated.resources.error_remote_unknown
import booklibrary.composeapp.generated.resources.error_request_timeout
import booklibrary.composeapp.generated.resources.error_serialization
import booklibrary.composeapp.generated.resources.error_server_error
import booklibrary.composeapp.generated.resources.error_too_many_requests
import com.gyadam.booklibrary.core.domain.DataError

fun DataError.toUiText(): UiText {
    val stringRes = when (this) {
        DataError.Local.DISK_FULL -> Res.string.error_disk_full
        DataError.Local.UNKNOWN -> Res.string.error_local_unknown
        DataError.Remote.REQUEST_TIMEOUT -> Res.string.error_request_timeout
        DataError.Remote.TOO_MANY_REQUESTS -> Res.string.error_too_many_requests
        DataError.Remote.NO_INTERNET -> Res.string.error_no_internet
        DataError.Remote.SERVER_ERROR -> Res.string.error_server_error
        DataError.Remote.SERIALIZATION -> Res.string.error_serialization
        DataError.Remote.UNKNOWN -> Res.string.error_remote_unknown
    }
    return UiText.StringResourceId(stringRes)
}