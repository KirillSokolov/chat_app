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

        override fun onNavigateHome() {
            navigateSingleTopTo(BOTTOM_MENU_ROUTE)
        }
    }

fun NavHostController.authNavigator()
        : AuthNavigator = object : AuthNavigator {
    override fun onNavigateAfterLogin() {
        popBackStack()
        navigateSingleTopTo(BOTTOM_MENU_ROUTE)
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
        override fun onNavigateToDetails() {
            navigateToDetails()
        }

        override fun onNavigateToEdit() {
            TODO("Not yet implemented")
        }

        override fun onNavigateToAbout() {
            TODO("Not yet implemented")
        }

        override fun onLogout() {
            popBackStack()
            navigateToLogout()
        }

        override fun onChatList() {
            navigateToChatList()
        }

        override fun onChat() {
            navigateToChat()
        }
    }