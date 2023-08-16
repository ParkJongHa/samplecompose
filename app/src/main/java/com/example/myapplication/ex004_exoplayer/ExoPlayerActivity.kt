package com.example.myapplication.ex004_exoplayer

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class ExoPlayerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                VideoView()
            }
        }
    }

}

@Composable
fun VideoView() {
    val videoUri = Uri.parse("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4")
    VideoPlayer(uri = videoUri)
}

@Composable
fun VideoPlayer(uri: Uri) {
    val context = LocalContext.current
    val mediaItem = MediaItem.fromUri(uri)
    val player = ExoPlayer.Builder(context).build()

    AndroidView({ // context ->
        PlayerView(context).apply{
            this.player = player
        }
    }, modifier = Modifier.fillMaxSize())

    player.setMediaItem(mediaItem)
    player.prepare()
    player.play()
}