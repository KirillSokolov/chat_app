package com.test.authorization.presentation.ui

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.test.authorization.di.DaggerAuthorizationComponent
import com.test.authorization.presentation.viewmodel.SendAuthCodeViewModel
import com.test.authorization.presentation.viewmodel.SendAuthCodeViewModelFactory
import com.test.navigation.AUTH_ROUTE
import com.test.navigation.AuthNavigator
import com.test.navigation.SignInDestination
import com.test.navigation.SignUpDestination
import javax.inject.Inject

fun NavGraphBuilder.authentication(externalNavigator: AuthNavigator) {
    navigation(startDestination = SignInDestination.route, route = AUTH_ROUTE) {
        signIn(
            onSignedIn = externalNavigator::onNavigateAfterLogin,
            onNavigateToRegister = externalNavigator::onNavigateToRegister,
            onNavigateToReset = externalNavigator::onNavigateToReset,
        )
        signUp(onRegisterClick = externalNavigator::onNavigateUp)
        resetPassword(onResetClick = externalNavigator::onNavigateUp)
    }
}


private fun NavGraphBuilder.signIn(
    onSignedIn: () -> Unit, onNavigateToRegister: () -> Unit, onNavigateToReset: () -> Unit
) {
    composable(route = SignInDestination.route) {
        val component = DaggerAuthorizationComponent.builder().build()
        val viewModel = viewModelFactory{component.getSendAuthViewModelFactory()}

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        SignInScreen(
            uiState = uiState,
            onLoginClick = onSignedIn,
            onRegisterClick = onNavigateToRegister,
            onResetClick = onNavigateToReset,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
        )
    }
}

private fun NavGraphBuilder.signUp(onRegisterClick: () -> Unit) {
    composable(route = SignUpDestination.route) {
        val viewModel: SignUpViewModel = viewModel()
        SignUpScreen(
            onRegisterClick = onRegisterClick,
            onFirstNameChange = viewModel::onFirstNameChange,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange
        )
    }
}

private fun NavGraphBuilder.resetPassword(
    onResetClick: () -> Unit
) {
//    composable(route = ResetPasswordDestination.route) {
//        val viewModel: ResetPasswordViewModel = viewModel()
//        ResetPasswordScreen(onResetClick)
//    }
}