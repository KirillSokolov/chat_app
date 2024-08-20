package com.test.user.details.presentation.navigation

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.test.navigation.AboutDestination
import com.test.navigation.DetailsDestination
import com.test.user.details.di.DaggerUserDetailsComponent
import com.test.user.details.di.UserDetailsFeatureDepsProvider
import com.test.user.details.presentation.ui.DetailsScreen
import com.test.user.details.presentation.viewmodel.UserDetailsViewModel

fun NavGraphBuilder.details(
    onNavigate: () -> Unit, onBack: () -> Unit
) {
    composable(route = DetailsDestination.route) {
        val component = DaggerUserDetailsComponent.builder().addDeps(
            UserDetailsFeatureDepsProvider.deps).build()
        val viewModel:UserDetailsViewModel = viewModel(factory = component.getUserDetailsViewModelFactory())
        viewModel.load()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        val showPhotoPicker = remember { mutableStateOf(false) }

        val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> viewModel.onPhotoChange(uri) }
        )

        if(showPhotoPicker.value) {
            showPhotoPicker.value = false
            LaunchPhotoPicker(singlePhotoPickerLauncher)
        }

        DetailsScreen(
            uiState = uiState,
            onNicknameChange = {},
            onNameChange = {},
            onPhotoPick = {showPhotoPicker.value = true}
        )
    }
}

@Composable
private fun LaunchPhotoPicker(
    singlePhotoPickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>
) {
    singlePhotoPickerLauncher.launch(
        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
}

fun NavGraphBuilder.editDetails(
    onNavigate: () -> Unit, onBack: () -> Unit
) {
//    composable(route = EditDestination.route) {
//        val component = DaggerAuthorizationComponent.builder().addDeps(
//            AuthorizationFeatureDepsProvider.deps
//        ).build()
//        val viewModel: SendAuthCodeViewModel =
//            viewModel(factory = component.getSendAuthViewModelFactory())
//
//        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//
//        val errorState by viewModel.errorState.collectAsStateWithLifecycle()
//
//        if (errorState.msg.isNotEmpty()) {
//            Toast(errorState.msg)
//        } else if (errorState.code != 0) {
//            Toast(errorState.code)
//        }
//
//        SendCodeScreen(
//            uiState = uiState,
//            onLoginClick = {
//                sendAuthCode(
//                    viewModel,
//                    "${uiState.code} ${uiState.phone}",
//                    onSignedIn
//                )
//            },
//            onRegisterClick = onNavigateToRegister,
//            onResetClick = onNavigateToReset,
//            onCodeChange = viewModel::onCodeChange,
//            onPhoneChange = viewModel::onPhoneChange,
//        )
    //  }
}

fun NavGraphBuilder.aboutUser(
    onBack: () -> Unit
) {
    composable(route = AboutDestination.route) {
//        val component = DaggerChatListComponent.builder().addDeps(
//            ChatListFeatureDepsProvider.deps).build()
//        val viewModel:SendAuthCodeViewModel = viewModel(factory = component.getSendAuthViewModelFactory())
//
//        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//
//        val errorState by viewModel.errorState.collectAsStateWithLifecycle()
//
//        if(errorState.msg.isNotEmpty()){
//            Toast(errorState.msg)
//        }else if(errorState.code != 0){
//            Toast(errorState.code)
//        }

//        SendCodeScreen(
//            uiState = uiState,
//            onLoginClick = {sendAuthCode(viewModel, "${uiState.code} ${uiState.phone}", onSignedIn)} ,
//            onRegisterClick = onNavigateToRegister,
//            onResetClick = onNavigateToReset,
//            onCodeChange = viewModel::onCodeChange,
//            onPhoneChange = viewModel::onPhoneChange,
//        )
    }
}