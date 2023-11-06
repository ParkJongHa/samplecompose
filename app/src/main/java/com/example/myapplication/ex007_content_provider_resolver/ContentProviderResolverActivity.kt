package com.example.myapplication.ex007_content_provider_resolver

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ex002_variable_hold.VariableHoldViewModel

class ContentProviderResolverActivity : ComponentActivity() {

    private val contentProviderViewModel: ContentProviderViewModel by lazy {
        ViewModelProvider(this)[ContentProviderViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(Modifier.fillMaxSize()) {
                LazyColumn(
                    Modifier.fillMaxSize()
                ) {
                    item {
                        ContentAuthority(contentProviderViewModel, this@ContentProviderResolverActivity)
                    }
                    item {
                        StartBrowser()
                    }
                }
            }
        }
    }
}

@Composable
fun ContentAuthority(
    contentProviderViewModel: ContentProviderViewModel,
    context: Context
) {
    Button(onClick = {contentProviderViewModel.fetchProvider(context)}) {
        Text("ContentAuthority")
    }
}

@Composable
fun StartBrowser() {
    val context = LocalContext.current
    val starter: () -> Unit = {
        context.startActivity(Intent("com.sec.android.app.sbrowser"))
    }
    Button(onClick = {starter()}) {
        Text("StartBrowser")
    }
}
