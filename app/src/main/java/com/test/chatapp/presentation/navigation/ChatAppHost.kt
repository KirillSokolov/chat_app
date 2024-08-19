package com.test.chatapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.test.authorization.presentation.ui.start
import com.test.navigation.StartDestination
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.authorization.presentation.navigation.authentication
import com.test.navigation.authNavigator
import com.test.navigation.startNavigator


@Composable
fun ChatHost(
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = StartDestination.route
    ) {
        start(navController.startNavigator())
        authentication(navController.authNavigator())
     //   bottom_menu(navController.bottomNavigator())
    }
}