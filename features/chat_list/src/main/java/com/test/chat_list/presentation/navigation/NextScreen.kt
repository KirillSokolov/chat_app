package com.test.chat_list.presentation.navigation

internal sealed class NextScreen {
    data object Nothing : NextScreen()
    class ChatFragment() : NextScreen()
    class UserFragment() : NextScreen()
}
