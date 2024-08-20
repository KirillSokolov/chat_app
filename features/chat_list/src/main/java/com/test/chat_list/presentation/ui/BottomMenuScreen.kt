package com.test.chat_list.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.test.chat_list.di.ChatListFeatureDepsProvider
import com.test.chat_list.di.DaggerChatListComponent
import com.test.chat_list.presentation.viewmodel.BottomMenuViewModel
import com.test.chat_list.presentation.viewmodel.MenuViewModel
import com.test.navigation.BottomMenuNavigator
import com.test.navigation.MENU_GRAPH
import com.test.navigation.MenuDestination
import com.test.navigation.MenuNavigator
import com.test.navigation.navigateSingleTopTo
import com.test.ui.BottomState
import com.test.ui.design.SystemBarsColorDisposableEffect
import com.test.ui.design.theme.*

fun NavGraphBuilder.menu(externalNavigator: MenuNavigator) {

    navigation(startDestination = MenuDestination.route, route = MENU_GRAPH) {
        composable(route = MenuDestination.route) {
            BottomState.addBottomItems()
            val viewModel: MenuViewModel = viewModel(
                factory = MenuViewModel.Factory()
            )

            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            MenuScreen(
                uiState = uiState,
                onSectionSelect = viewModel::onSectionSelected,
                onPromotionClick = externalNavigator::navigateToPromotion,
                onItemSelected = externalNavigator::navigateToDetails,
                onAddToCartClicked = viewModel::addToCart
            )
        }
        // This is an example of the promo as an inner page
        // promotion()
    }

}


@Composable
fun BottomMenuScreen(externalNavigator: BottomMenuNavigator) {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.parent?.route
        ?: navBackStackEntry?.destination?.route

    val bottomMenuViewModel: BottomMenuViewModel = viewModel(
        factory = BottomMenuViewModel.Factory()
    )
    val bottomMenuUiState by bottomMenuViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(bottomBar = {
        ChatBottomBar(
            bottomUiState = bottomMenuUiState,
            currentDestination = currentRoute,
            onNavigateToTopLevel = { route ->
                navController.navigateSingleTopTo(route)
            })
    }) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = MENU_GRAPH
            ) {
                //menu(navController.menuNavigator(externalNavigator))
               // chatList()
               // chat()
               // profile(navController.profileNavigator(externalNavigator))
            }

            SystemBarsColorDisposableEffect(true)
        }
    }
}

@Preview
@Composable
fun BottomMenuScreenPreview() {
    ChatAppTheme {
        BottomMenuScreen(
            externalNavigator = object: BottomMenuNavigator{
                override fun onNavigateToDetails() {
                    TODO("Not yet implemented")
                }

                override fun onNavigateToEdit() {
                    TODO("Not yet implemented")
                }

                override fun onNavigateToAbout() {
                    TODO("Not yet implemented")
                }

                override fun onLogout() {
                    TODO("Not yet implemented")
                }

                override fun onChatList() {
                    TODO("Not yet implemented")
                }

                override fun onChat() {
                    TODO("Not yet implemented")
                }

            }

        )
    }
}