package com.gyadam.booklibrary.bookLibrary.presentation.bookDetails.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import booklibrary.composeapp.generated.resources.Res
import booklibrary.composeapp.generated.resources.book_cover_descp
import booklibrary.composeapp.generated.resources.go_back_content_descp
import coil3.compose.rememberAsyncImagePainter
import com.gyadam.booklibrary.core.presentation.DarkBlue
import com.gyadam.booklibrary.core.presentation.DesertWhite
import org.jetbrains.compose.resources.stringResource

@Composable
fun BlurredImageBackgroud(
    imageUrl: String,
    isFavourite: Boolean,
    onFavouriteClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }

    var painter = rememberAsyncImagePainter(
        model = imageUrl,
        onSuccess = {
            val size = it.painter.intrinsicSize
            imageLoadResult = if (size.width > 1 && size.height > 1) Result.success(it.painter) else
                Result.failure(Exception("Invalid image size"))
        }
    )
    Box {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(0.3f).fillMaxWidth().background(DarkBlue)) {
                imageLoadResult?.getOrNull()?.let { painter ->
                    Image(
                        painter = painter,
                        contentDescription = stringResource(Res.string.book_cover_descp),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                            .blur(20.dp)
                    )
                }

            }
            Box(modifier = Modifier.weight(0.7f).fillMaxWidth().background(DesertWhite))

        }
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(androidx.compose.ui.Alignment.TopStart)
                .padding(top = 16.dp, start = 16.dp)
                .statusBarsPadding()
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(Res.string.go_back_content_descp),
                tint = Color.White
            )
        }


    }

}