package com.example.myapplication.ex006_slide

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class SlideActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                LazyColumn(
                    Modifier.fillMaxSize()
                ) {
                    item {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()) {
                            ContentOption()
                            SlideBox(modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .background(Color(0xFFFF9900)),
                                offsetMinX = 48.dp,
                                offsetMaxX = 48.dp
                            ) {
                                Content()
                            }
                        }
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()) {
                            ContentOption()
                            SlideBox(modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .background(Color(0xFFFF9900)),
                                offsetMinX = 48.dp,
                                offsetMaxX = 48.dp
                            ) {
                                Content()
                            }
                        }
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()) {
                            ContentOption()
                            SlideBox(modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .background(Color(0xFFFF9900)),
                                offsetMinX = 48.dp,
                                offsetMaxX = 48.dp
                            ) {
                                Content()
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ContentOption() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.Yellow)
    ) {
        Row(
            Modifier
                .width(100.dp)
                .fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {

            Image(
                painter = painterResource(androidx.constraintlayout.widget.R.drawable.abc_ic_ab_back_material),
                contentDescription = null,
                modifier = Modifier
                    .width(48.dp)
                    .wrapContentHeight()
                    .clickable { Log.w("click", "image") }
                ,
            )

        }
    }
}

@Composable
fun Content() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.Gray)
    ) {
        Row(
            Modifier
                .width(100.dp)
                .fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {

            Image(
                painter = painterResource(androidx.constraintlayout.widget.R.drawable.abc_ic_clear_material),
                contentDescription = null,
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .clickable { Log.w("click", "image") }
                ,
            )

            Text("Slide Test", color = Color.White,
                modifier = Modifier
                    .clickable { Log.w("click", "text") }
            )

        }
    }
}
