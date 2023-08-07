package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ex001_gif.GifActivity
import com.example.myapplication.ex002_variable_hold.VariableHoldActivity

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
    val context = LocalContext.current

    LazyColumn(Modifier.fillMaxSize().padding(start = 5.dp, end = 5.dp)) {
        item{
            Button(onClick = { context.startActivity(Intent(context, GifActivity::class.java)) })
                {Text(GifActivity::class.simpleName.toString())}

            Button(onClick = { context.startActivity(Intent(context, VariableHoldActivity::class.java)) })
                {Text(VariableHoldActivity::class.simpleName.toString())}

        }
    }
}
