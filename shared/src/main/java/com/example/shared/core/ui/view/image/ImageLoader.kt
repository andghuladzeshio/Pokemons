package com.example.shared.core.ui.view.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@Composable
fun ImageLoader(
    modifier: Modifier = Modifier,
    url: String,
    contentScale: ContentScale,
    onLoading: @Composable () -> Unit
) {
    SubcomposeAsyncImage(
        contentScale = contentScale, modifier = modifier, model = url, contentDescription = null
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            onLoading()
        } else {
            SubcomposeAsyncImageContent()
        }
    }
}