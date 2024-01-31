package com.example.myapplication.ex010_biometric_prompt

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import java.util.concurrent.Executor


class BiometricPromptViewModel: ViewModel() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var biometricPromptCallback: BiometricPrompt.AuthenticationCallback
    @RequiresApi(Build.VERSION_CODES.R)
    fun req(context: Context) {
        executor = ContextCompat.getMainExecutor(context)

        biometricPromptCallback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                println("onAuthenticationError errorCode:$errorCode, errString:$errString")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                println("onAuthenticationSucceeded result:$result")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                println("onAuthenticationFailed")
            }
        }

        biometricPrompt = BiometricPrompt(context as FragmentActivity, executor, biometricPromptCallback)

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.
//        val biometricLoginButton =
//            findViewById<Button>(R.id.biometric_login)
//        biometricLoginButton.setOnClickListener {
//            biometricPrompt.authenticate(promptInfo)
//        }
        biometricPrompt.authenticate(promptInfo)
    }
}