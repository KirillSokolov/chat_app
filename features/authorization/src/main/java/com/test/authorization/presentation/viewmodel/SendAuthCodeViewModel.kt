package com.test.authorization.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.authorization.domain.SendAuthCodeUseCase
import com.test.authorization.presentation.navigation.sendcode.NextScreen
import com.test.data.temp.UserData
import com.test.domain.models.request.sendAuthCodeBuilder
import com.test.domain.models.response.SendAuthCodeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SignInUiState(
    val code: String,
    val phone: String
)

internal class SendAuthCodeViewModel (private val useCase: SendAuthCodeUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow(
        SignInUiState(
            phone = "",
            code = ""
        )
    )

    val uiState = _uiState.asStateFlow()

    fun onCodeChange(code: String) {
        _uiState.update {
            it.copy(
                code = code
            )
        }
    }

    fun onPhoneChange(phone: String) {
        _uiState.update {
            it.copy(
                phone = phone
            )
        }
    }

    private val _screen = MutableLiveData<NextScreen>()
    val screen: LiveData<NextScreen> = _screen

    fun sendAuthCode(phone: String) {
        val sendAuthCode = sendAuthCodeBuilder {
            this.phone = phone
        }

        viewModelScope.launch {
            when(val result = useCase.execute(sendAuthCode)){
                is SendAuthCodeResponse.Authorization -> {
                    UserData.phone = phone
                    _screen.value = NextScreen.CheckAuthCodeFragment()
                    _screen.value = NextScreen.Nothing
                }
                is SendAuthCodeResponse.Error -> {
                    _screen.value = NextScreen.Error(msg = result.message, code = result.code)
                    _screen.value = NextScreen.Nothing
                }
            }
        }

    }
}

internal class SendAuthCodeViewModelFactory(private val sendAuthCodeUseCase: SendAuthCodeUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        return SendAuthCodeViewModel(
            useCase = sendAuthCodeUseCase
        ) as T
    }
}