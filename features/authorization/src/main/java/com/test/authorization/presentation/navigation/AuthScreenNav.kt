package com.test.authorization.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.test.authorization.di.AuthorizationFeatureDepsProvider
import com.test.authorization.di.DaggerAuthorizationComponent
import com.test.authorization.presentation.ui.CheckCodeScreen
import com.test.authorization.presentation.ui.SendCodeScreen
import com.test.authorization.presentation.viewmodel.SendAuthCodeViewModel
import com.test.navigation.AUTH_ROUTE
import com.test.navigation.AuthNavigator
import com.test.navigation.CheckCodeDestination
import com.test.navigation.SignInDestination

fun NavGraphBuilder.authentication(externalNavigator: AuthNavigator) {
    navigation(startDestination = SignInDestination.route, route = AUTH_ROUTE) {
        signIn(
            onSignedIn = externalNavigator::onNavigateToCheckCode,
            onNavigateToRegister = externalNavigator::onNavigateToRegister,
            onNavigateToReset = externalNavigator::onNavigateToReset,
        )
        checkCode(onVerifyClick = externalNavigator::onNavigateAfterLogin)
        resetPassword(onResetClick = externalNavigator::onNavigateUp)
    }
}


private fun NavGraphBuilder.signIn(
    onSignedIn: () -> Unit, onNavigateToRegister: () -> Unit, onNavigateToReset: () -> Unit
) {
    composable(route = SignInDestination.route) {
        val component = DaggerAuthorizationComponent.builder().addDeps(
            AuthorizationFeatureDepsProvider.deps).build()
        val viewModel:SendAuthCodeViewModel = viewModel(factory = component.getSendAuthViewModelFactory())

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        SendCodeScreen(
            uiState = uiState,
            onLoginClick = onSignedIn,
            onRegisterClick = onNavigateToRegister,
            onResetClick = onNavigateToReset,
            onCodeChange = viewModel::onCodeChange,
            onPhoneChange = viewModel::onPhoneChange,
        )
    }
}

private fun NavGraphBuilder.checkCode(
    onVerifyClick: () -> Unit
) {
    composable(route = CheckCodeDestination.route) {
        val component = DaggerAuthorizationComponent.builder().addDeps(
            AuthorizationFeatureDepsProvider.deps).build()
        val viewModel:SendAuthCodeViewModel = viewModel(factory = component.getSendAuthViewModelFactory())

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        CheckCodeScreen(
            uiState = uiState,
            onVerifyClick = onVerifyClick,
            onCodeChange = viewModel::onCodeChange
        )
    }
}

//private fun NavGraphBuilder.signUp(onRegisterClick: () -> Unit) {
//    composable(route = SignUpDestination.route) {
//        val viewModel: SignUpViewModel = viewModel()
//        SignUpScreen(
//            onRegisterClick = onRegisterClick,
//            onFirstNameChange = viewModel::onFirstNameChange,
//            onEmailChange = viewModel::onEmailChange,
//            onPasswordChange = viewModel::onPasswordChange
//        )
//    }
//}

private fun NavGraphBuilder.resetPassword(
    onResetClick: () -> Unit
) {
//    composable(route = ResetPasswordDestination.route) {
//        val viewModel: ResetPasswordViewModel = viewModel()
//        ResetPasswordScreen(onResetClick)
//    }
}