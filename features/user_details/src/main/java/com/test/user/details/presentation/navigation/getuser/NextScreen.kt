package com.test.user.details.presentation.navigation.getuser

internal sealed class NextScreen {
    data object Nothing : NextScreen()
    class ChatListFragment : NextScreen()
    class UserDetailsEditFragment : NextScreen()
}