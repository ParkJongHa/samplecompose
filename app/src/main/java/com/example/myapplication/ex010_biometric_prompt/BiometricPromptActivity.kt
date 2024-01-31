package com.example.myapplication.ex010_biometric_prompt

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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider

class BiometricPromptActivity : ComponentActivity() {

    private val biometricPromptViewModel: BiometricPromptViewModel by lazy {
        ViewModelProvider(this)[BiometricPromptViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(Modifier.fillMaxSize()) {
                Column(Modifier.fillMaxSize()) {
                    ReqButton(biometricPromptViewModel)
                }
            }
        }
    }
}

@Composable
fun ReqButton(
    biometricPromptViewModel: BiometricPromptViewModel,
) {
    val context = LocalContext.current

    Button(onClick = {biometricPromptViewModel.req(context)}) {
        Text("Ktor Req Button")
    }
}
