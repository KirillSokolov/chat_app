package com.test.authorization.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.authorization.domain.CheckAuthCodeUseCase
import com.test.authorization.domain.SaveRefreshTokenUseCase
import com.test.authorization.presentation.navigation.checkcode.NextScreen
import com.test.data.temp.UserData
import com.test.domain.models.request.checkAuthCodeBuilder
import com.test.domain.models.response.CheckAuthCodeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CheckCodeUiState(
    val code: String
)

internal class CheckAuthCodeViewModel (private val useCase: CheckAuthCodeUseCase, private val saveTokenUseCase: SaveRefreshTokenUseCase) :
    ViewModel() {

    private val _errorState = MutableStateFlow(
        SendCodeError(
            msg = "",
            code = 0
        )
    )

    private val _uiState = MutableStateFlow(
        CheckCodeUiState(
            code = ""
        )
    )

    val errorState = _errorState.asStateFlow()
    val uiState = _uiState.asStateFlow()

    fun onCodeChange(code: String) {
        _uiState.update {
            it.copy(
                code = code
            )
        }
    }

    fun onErrorCodeChange(code: Int) {
        _errorState.update {
            it.copy(
                code = code
            )
        }
    }

    fun checkAuthCode(code: String, onVerifySuccess: () -> Unit, onVerifyError: () -> Unit) {
        val checkAuthCode = checkAuthCodeBuilder {
            this.phone = UserData.phone
            this.code = code
        }
        viewModelScope.launch {
            when (val result = useCase.execute(checkAuthCode)) {
                is CheckAuthCodeResponse.Error -> {
                    _errorState.update {
                        it.copy(
                            msg = result.message,
                            code = result.code
                        )
                    }
                }

                is CheckAuthCodeResponse.Verification -> {
                    checkExistUser(result, onVerifySuccess, onVerifyError)
                }
            }
        }
    }

    private fun checkExistUser(verification: CheckAuthCodeResponse.Verification, onVerifySuccess: () -> Unit, onVerifyError: () -> Unit) {
        if (verification.isUserExists) {
            viewModelScope.launch {
                saveTokenUseCase.execute(verification.refreshToken)
            }
            onVerifySuccess()
        }else{
            onVerifyError()
        }
    }
}


internal class CheckAuthCodeViewModelFactory(private val checkAuthCodeUseCase: CheckAuthCodeUseCase, private val saveTokenUseCase: SaveRefreshTokenUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        return CheckAuthCodeViewModel(
            useCase = checkAuthCodeUseCase,
            saveTokenUseCase = saveTokenUseCase
        ) as T
    }
}