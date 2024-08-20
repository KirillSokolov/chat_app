package com.test.navigation

private const val ROUTE = "start"


data object StartDestination : ChatAppDestination{
    override val route: String = ROUTE
}

interface StartNavigator{
    fun onNavigateAfterStarted()
    fun onNavigateHome()

}

