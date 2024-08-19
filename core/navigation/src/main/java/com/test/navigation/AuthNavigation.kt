package com.test.navigation

import androidx.navigation.NavHostController

const val AUTH_ROUTE = "authentication"
const val AUTH_SIGN_IN = "/signin"
const val AUTH_SIGN_UP = "/signup"
const val AUTH_RESET_PASSWORD = "/resetpassword"

fun NavHostController.navigateToAuth() {
    navigateSingleTopTo(AUTH_ROUTE)
}

fun NavHostController.navigateToRegister() {
    navigate(SignUpDestination.route)
}

fun NavHostController.navigateToReset() {
    navigate(ResetPasswordDestination.route)
}


data object SignInDestination : ChatAppDestination {
    override val route = "$AUTH_ROUTE$AUTH_SIGN_IN"
}

data object SignUpDestination : ChatAppDestination {
    override val route = "$AUTH_ROUTE$AUTH_SIGN_UP"
}

private data object ResetPasswordDestination : ChatAppDestination {
    override val route = "$AUTH_ROUTE$AUTH_RESET_PASSWORD"
}

interface AuthNavigator {
    fun onNavigateAfterLogin()
    fun onNavigateToRegister()
    fun onNavigateToReset()
    fun onNavigateUp()
}



