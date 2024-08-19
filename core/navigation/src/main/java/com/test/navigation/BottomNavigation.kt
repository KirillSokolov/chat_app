package com.test.navigation


const val BOTTOM_MENU_ROUTE = "bottom_menu"

private object BottomMenuDestination : ChatAppDestination {
    override val route = BOTTOM_MENU_ROUTE
}


interface BottomMenuNavigator {
    fun onNavigateToDetails(menuItemId: String)
    fun onLogout()
    fun onNavigateUp()
}


//fun NavGraphBuilder.bottom_menu(externalNavigator: BottomMenuNavigator) {
//    composable(BottomMenuDestination.route) {
//        BottomMenuScreen(externalNavigator)
//    }
//    details(onNavigateUp = externalNavigator::onNavigateUp)
//}