package com.test.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavHostController


interface Router{
    fun navigateTo(url: String)
}

data class ChatTopDestinationsCollection(val items: List<ChatTopLevelDestination>)


interface TopLevelDestinationWithCount{
    val badgeValue: Int
    fun copyWithNewBadge(value: Int): ChatTopLevelDestination
}

interface ChatTopLevelDestination{
    val graph: String
    @get:DrawableRes
    val iconId: Int
    @get:StringRes
    val titleId: Int
}


fun NavHostController.startNavigator(): StartNavigator =
    object : StartNavigator {
        override fun onNavigateAfterStarted() {
            popBackStack()
            navigateToAuth()
        }
    }

fun NavHostController.authNavigator()
        : AuthNavigator = object : AuthNavigator {
    override fun onNavigateAfterLogin() {
        popBackStack()
        //navigateToCheckCode()
       // navigateSingleTopTo(BOTTOM_MENU_ROUTE)
    }

    override fun onNavigateToRegister() {
        navigateToRegister()
    }

    override fun onNavigateToCheckCode() {
        navigateToCheckCode()
    }

    override fun onNavigateToReset() {
        navigateToReset()
    }

    override fun onNavigateUp() {
        popBackStack()
    }
}
//
fun NavHostController.bottomNavigator(): BottomMenuNavigator =
    object : BottomMenuNavigator {
        override fun onNavigateToDetails(menuItemId: String) {
           // navigateToDetails(menuItemId = menuItemId)
        }

        override fun onLogout() {
            popBackStack()
            navigateToAuth()
        }

        override fun onNavigateUp() {
            popBackStack()
        }
    }