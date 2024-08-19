package com.test.chatapp.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.test.chatapp.presentation.navigation.ChatHost
import com.test.ui.design.theme.ChatAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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