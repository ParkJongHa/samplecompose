package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication.ex001_gif.GifActivity
import com.example.myapplication.ex002_variable_hold.VariableHoldActivity
import com.example.myapplication.ex003_youtube_player.YoutubePlayerActivity
import com.example.myapplication.ex004_exoplayer.ExoPlayerActivity
import com.example.myapplication.ex005_image.ImageActivity
import com.example.myapplication.ex006_slide.SlideActivity
import com.example.myapplication.ex007_content_provider_resolver.ContentProviderResolverActivity
import com.example.myapplication.ex008_ktor.KtorActivity
import kotlin.reflect.KClass

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ActivityList()
            }
        }
    }
}

@Composable
fun ActivityList() {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(start = 5.dp, end = 5.dp)) {
        item {
            ContentProviderResolverActivity::class.StartBtn()
            GifActivity::class.StartBtn()
            VariableHoldActivity::class.StartBtn()
            YoutubePlayerActivity::class.StartBtn()
            ExoPlayerActivity::class.StartBtn()
            ImageActivity::class.StartBtn()
            SlideActivity::class.StartBtn()
            KtorActivity::class.StartBtn()
        }
    }
}

@Composable
fun KClass<*>.StartBtn() {
    val context = LocalContext.current

    Button(onClick = {
        context.startActivity(Intent(context, this.java)
            .addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
        )

    }
    ) {
        Text(this@StartBtn.simpleName!!)
    }
}