package com.example.myapplication.ex008_ktor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider

class KtorActivity : ComponentActivity() {

    private val ktorViewModel: KtorViewModel by lazy {
        ViewModelProvider(this)[KtorViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(Modifier.fillMaxSize()) {
                Column(
                    Modifier.fillMaxSize()
                ) {
                    ReqButton(ktorViewModel)
                }
            }
        }
    }
}

@Composable
fun ReqButton(
    ktorViewModel: KtorViewModel,
) {
    Button(onClick = {ktorViewModel.req()}) {
        Text("Ktor Req Button")
    }
}
