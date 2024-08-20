package com.test.navigation

import androidx.navigation.NavHostController


const val BOTTOM_MENU_ROUTE = "bottom_menu"


const val MENU_DETAILS = "/details"
const val MENU_EDIT_DETAILS = "/edit_details"
const val MENU_ABOUT = "/about_details"
const val MENU_LOGOUT = "/logout"
const val MENU_CHAT_LIST = "/chatList"
const val MENU_CHAT = "/chat"

fun NavHostController.navigateToChat() {
    navigate(ChatDestination.route)
}

fun NavHostController.navigateToLogout() {
    navigate(LogoutDestination.route)
}

fun NavHostController.navigateToChatList() {
    navigate(ChatListDestination.route)
}

fun NavHostController.navigateToDetails() {
    navigate(DetailsDestination.route)
}

fun NavHostController.navigateToAbout() {
    navigate(AboutDestination.route)
}

fun NavHostController.navigateToEdit() {
    navigate(EditDestination.route)
}
object BottomMenuDestination : ChatAppDestination {
    override val route = BOTTOM_MENU_ROUTE
}

data object DetailsDestination : ChatAppDestination {
    override val route = "$BOTTOM_MENU_ROUTE$MENU_DETAILS"
}
data object EditDestination : ChatAppDestination {
    override val route = "$BOTTOM_MENU_ROUTE$MENU_EDIT_DETAILS"
}

data object AboutDestination : ChatAppDestination {
    override val route = "$BOTTOM_MENU_ROUTE$MENU_ABOUT"
}
data object LogoutDestination : ChatAppDestination {
    override val route = "$BOTTOM_MENU_ROUTE$MENU_LOGOUT"
}

data object ChatDestination : ChatAppDestination {
    override val route = "$BOTTOM_MENU_ROUTE$MENU_CHAT"
}
data object ChatListDestination : ChatAppDestination {
    override val route = "$BOTTOM_MENU_ROUTE$MENU_CHAT_LIST"
}

interface BottomMenuNavigator {
    fun onNavigateToDetails()
    fun onNavigateToEdit()
    fun onNavigateToAbout()
    fun onLogout()
    fun onChatList()
    fun onChat()
}


