package com.test.user.details.presentation.navigation.edituser

internal sealed class NextScreen {
    data object Nothing : NextScreen()
    class UserDetailsFragment : NextScreen()
}