package com.example.myapplication.ex001_gif

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import coil.size.Size

class GifActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(Modifier.fillMaxSize()) {
                    Text("Gif Test")
                    Text(" ")
                    GifImage()
                }
            }
        }
    }
}

@Composable
fun GifImage(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    val model: ImageRequest = ImageRequest // model
        .Builder(context)
        .data("https://www.galvanizeaction.org/wp-content/uploads/2022/06/Wow-gif.gif")
        .apply(block = {size(Size.ORIGINAL)})
        .build()

    val imageLoader: ImageLoader = ImageLoader.Builder(context) // imageLoader
        .components {add(GifDecoder.Factory())}
        .build()

    Image(
        painter = rememberAsyncImagePainter(model, imageLoader),
        contentDescription = null,
        modifier = modifier.wrapContentWidth(),
    )
}