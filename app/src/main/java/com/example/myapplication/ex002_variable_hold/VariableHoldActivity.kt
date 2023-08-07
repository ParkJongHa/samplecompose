package com.example.myapplication.ex002_variable_hold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider

class VariableHoldActivity : ComponentActivity() {

    private val variableHoldViewModel: VariableHoldViewModel by lazy {
        ViewModelProvider(this)[VariableHoldViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(Modifier.fillMaxSize()) {
                    ViewModelCount(variableHoldViewModel)
                    RememberCount()
                    ByRememberCount()
                    ValueSetValueRememberCount()
                    RememberSaveableCount()
                }
            }
        }
    }
}

@Composable
fun ViewModelCount(variableHoldViewModel: VariableHoldViewModel) {
    val mutableState = variableHoldViewModel.count_mutableStateOf

    Row(Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(mutableState.value.toString(), modifier = Modifier.weight(1f))
        Button(onClick = { mutableState.value++ }) {Text("MutableStateOfCount")}
    }
}

/*
컴포저블에서 MutableState 객체를 선언하는 데는 세 가지 방법
// 1. val mutableState = remember { mutableStateOf(default) }
// 2. var value by remember { mutableStateOf(default) }
// 3. val (value, setValue) = remember { mutableStateOf(default) }
// */
@Composable
fun RememberCount() {
    val mutableState = remember { mutableStateOf(0) } // 화면 회전시 초기화

    Row(Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(mutableState.value.toString(), modifier = Modifier.weight(1f))
        Button(onClick = { mutableState.value++ }) {Text("MutableStateOfRememberCount")}
    }

}

@Composable
fun ByRememberCount() {
    var mutableState by remember { mutableStateOf(0) } // 화면 회전시 초기화

    Row(Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(mutableState.toString(), modifier = Modifier.weight(1f))
        Button(onClick = { mutableState++ }) {Text("MutableStateOfByRememberCount")}
    }
}

@Composable
fun ValueSetValueRememberCount() {
    val (value, setValue) = remember { mutableStateOf(0) } // 화면 회전시 초기화

    Row(Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(value.toString(), modifier = Modifier.weight(1f))
        Button(onClick = { setValue(value+1) }) {Text("MutableStateOfValueSetValueRememberCount")}
    }
}





@Composable
fun RememberSaveableCount() {
    val mutableState = rememberSaveable { mutableStateOf(0) } // 화면 회전시

    Row(Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(mutableState.value.toString(), modifier = Modifier.weight(1f))
        Button(onClick = { mutableState.value++ }) {Text("rememberSaveableCount")}
    }
}