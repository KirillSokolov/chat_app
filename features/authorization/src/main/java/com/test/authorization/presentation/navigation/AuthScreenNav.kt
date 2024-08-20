package com.test.authorization.presentation.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.test.authorization.di.AuthorizationFeatureDepsProvider
import com.test.authorization.di.DaggerAuthorizationComponent
import com.test.authorization.presentation.ui.CheckCodeScreen
import com.test.authorization.presentation.ui.SendCodeScreen
import com.test.authorization.presentation.ui.RegistrationScreen
import com.test.authorization.presentation.viewmodel.CheckAuthCodeViewModel
import com.test.authorization.presentation.viewmodel.RegistrationViewModel
import com.test.authorization.presentation.viewmodel.SendAuthCodeViewModel
import com.test.authorization.presentation.viewmodel.SendCodeError
import com.test.domain.models.exception.IllegalArgumentCodeException
import com.test.domain.models.exception.IllegalArgumentNameException
import com.test.domain.models.exception.IllegalArgumentPhoneException
import com.test.domain.models.exception.IllegalArgumentUsernameException
import com.test.navigation.AUTH_ROUTE
import com.test.navigation.AuthNavigator
import com.test.navigation.CheckCodeDestination
import com.test.navigation.SignInDestination
import com.test.navigation.SignUpDestination

fun NavGraphBuilder.authentication(externalNavigator: AuthNavigator) {
    navigation(startDestination = SignInDestination.route, route = AUTH_ROUTE) {
        signIn(
            onSignedIn = externalNavigator::onNavigateToCheckCode,
            onNavigateToRegister = externalNavigator::onNavigateToRegister,
            onNavigateToReset = externalNavigator::onNavigateToReset,
        )
        signUp (
            onRegisterSuccess = externalNavigator::onNavigateAfterLogin
        )
        checkCode(
            onVerifySuccess = externalNavigator::onNavigateAfterLogin,
            onVerifyError = externalNavigator::onNavigateToRegister
        )
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

        val errorState by viewModel.errorState.collectAsStateWithLifecycle()

        if(errorState.msg.isNotEmpty()){
            Toast(errorState.msg)
        }else if(errorState.code != 0){
            Toast(errorState.code)
        }

        SendCodeScreen(
            uiState = uiState,
            onLoginClick = {sendAuthCode(viewModel, uiState.code, uiState.phone.text, onSignedIn)} ,
            onRegisterClick = onNavigateToRegister,
            onResetClick = onNavigateToReset,
            countryDetails = viewModel::onCodeChange,
            onPhoneChange = viewModel::onPhoneChange,
        )
    }
}

private fun sendAuthCode(viewModel: SendAuthCodeViewModel,code:String, phone: String, onSignedIn: () -> Unit, ) {
    try{
        viewModel.sendAuthCode(code, phone, onSignedIn)
    }catch (e: IllegalArgumentPhoneException){
        viewModel.onErrorCodeChange(com.test.chatapp.core.ui.R.string.error_phone)
       // Toast(com.test.chatapp.core.ui.R.string.error_phone)
    }
}

private fun NavGraphBuilder.checkCode(
    onVerifySuccess: () -> Unit, onVerifyError: () -> Unit
) {
    composable(route = CheckCodeDestination.route) {
        val component = DaggerAuthorizationComponent.builder().addDeps(
            AuthorizationFeatureDepsProvider.deps).build()
        val viewModel: CheckAuthCodeViewModel = viewModel(factory = component.getCheckAuthViewModelFactory())

        val errorState by viewModel.errorState.collectAsStateWithLifecycle()

        if(errorState.msg.isNotEmpty()){
            Toast(errorState.msg)
        }else if(errorState.code != 0){
            Toast(errorState.code)
        }

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        CheckCodeScreen(
            uiState = uiState,
            onVerifyClick = { sendCheckCode(viewModel, uiState.code, onVerifySuccess, onVerifyError) },
            onCodeChange = viewModel::onCodeChange
        )
    }
}

private fun sendCheckCode(
    viewModel: CheckAuthCodeViewModel,
    code: String,
    onVerifySuccess: () -> Unit,
    onVerifyError: () -> Unit
) {
    try{
        viewModel.checkAuthCode(code, onVerifySuccess, onVerifyError)
    }catch (e: IllegalArgumentCodeException){
        viewModel.onErrorCodeChange(com.test.chatapp.core.ui.R.string.error_code)
    }
}


private fun NavGraphBuilder.signUp(onRegisterSuccess: () -> Unit) {
    composable(route = SignUpDestination.route) {
        val component = DaggerAuthorizationComponent.builder().addDeps(
            AuthorizationFeatureDepsProvider.deps).build()
        val viewModel: RegistrationViewModel = viewModel(factory = component.getRegistrationViewModelFactory())
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        val errorState by viewModel.errorState.collectAsStateWithLifecycle()

        if(errorState.msg.isNotEmpty()){
            Toast(errorState)
        }else if(errorState.code != 0){
            Toast(errorState)
        }

        RegistrationScreen(
            uiState = uiState,
            onRegisterClick = {sendRegistrationData(viewModel, uiState.phone, uiState.name, uiState.nickName, onRegisterSuccess)},
            onNameChange = viewModel::onNameChange,
            onNicknameChange = viewModel::onNicknameChange
        )
    }
}



private fun sendRegistrationData(
    viewModel: RegistrationViewModel,
    phone: String,
    name: String,
    nickName: String,
    onRegisterSuccess: () -> Unit
) {
    try{
        viewModel.registration(phone, name, nickName, onRegisterSuccess)
    }catch (e: IllegalArgumentNameException){
        viewModel.onErrorCodeChange(com.test.chatapp.core.ui.R.string.error_name)
    }catch (e: IllegalArgumentUsernameException){
        viewModel.onErrorCodeChange(com.test.chatapp.core.ui.R.string.error_username)
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

@Composable
fun Toast(message: String) {
    val context = LocalContext.current

    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun Toast(errorState: SendCodeError) {
    val context = LocalContext.current
if (errorState.msg.isEmpty())
    Toast.makeText(context, context.getText(errorState.code), Toast.LENGTH_SHORT).show()
    else
    Toast.makeText(context, errorState.msg, Toast.LENGTH_SHORT).show()

}
@Composable
fun Toast(resId: Int) {
    val context = LocalContext.current

    Toast.makeText(context, context.getText(resId), Toast.LENGTH_SHORT).show()
}