package com.test.chatapp.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.test.chat_list.presentation.GetAccessTokenService
import com.test.chatapp.presentation.navigation.ChatHost
import com.test.ui.design.theme.ChatAppTheme

@SuppressLint("SourceLockedOrientationActivity")
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startService(Intent(this, GetAccessTokenService::class.java))
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
            }
        })

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ChatApp()
        }
    }
}

@Composable
fun ChatApp() {
    ChatAppTheme {
        ChatHost()
    }
}