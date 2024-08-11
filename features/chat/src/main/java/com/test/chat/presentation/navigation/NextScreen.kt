package com.test.chat.presentation.navigation

internal sealed class NextScreen {
    data object Nothing : NextScreen()
    class ChatListFragment : NextScreen()
}
