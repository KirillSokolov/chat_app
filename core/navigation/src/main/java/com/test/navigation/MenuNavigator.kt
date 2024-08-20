package com.test.navigation

const val MENU_GRAPH = "menu_graph"

data object MenuDestination : ChatAppDestination {
    override val route = "$MENU_GRAPH/menu"
}

interface MenuNavigator{
    fun navigateToPromotion()
    fun navigateToDetails(id:String)
    fun onNavigateUp()
}