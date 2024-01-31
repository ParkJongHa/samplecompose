package com.example.myapplication.ex011_sendbroadcast

import android.content.ComponentName
import android.content.Intent
import android.content.Intent.FLAG_INCLUDE_STOPPED_PACKAGES
import android.os.Bundle
import android.widget.Button
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

class SendBroadCastActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(Modifier.fillMaxSize()) {
                Column(Modifier.fillMaxSize()) {
                    SendBroadcastButton(this@SendBroadCastActivity)
                }
            }
        }
    }
}

@Composable
fun SendBroadcastButton(activity: ComponentActivity) {
    Button(onClick = {
        val intent = Intent().apply {
            //  .setComponent(ComponentName([앱 ID 패키지명], [브로드 캐스트 패키지명]));
            this.setComponent(ComponentName("your.app.package", "your.app.package.BroadcastReceiver"));
            this.addFlags(FLAG_INCLUDE_STOPPED_PACKAGES); //!! 죽은 앱을 호출
            this.action = "YOUR_CUSTOM_ACTION"
        }

        activity.sendBroadcast(intent)
    }) {
        Text("SendBroadCastActivity")
    }
}
