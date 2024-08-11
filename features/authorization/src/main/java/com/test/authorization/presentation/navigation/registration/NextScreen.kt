package com.test.authorization.presentation.navigation.registration

const val DEFAULT_CODE = 1

internal sealed class NextScreen {
    data object Nothing : NextScreen()
    class ChatListFragment : NextScreen()
    class Error(val msg: String, val code: Int = DEFAULT_CODE) : NextScreen()
}