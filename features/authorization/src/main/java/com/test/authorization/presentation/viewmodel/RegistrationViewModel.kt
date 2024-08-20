package com.test.authorization.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.authorization.domain.RegistrationUseCase
import com.test.authorization.domain.SaveRefreshTokenUseCase
import com.test.authorization.domain.SaveUserUseCase
import com.test.authorization.presentation.navigation.registration.NextScreen
import com.test.data.temp.UserData
import com.test.domain.models.request.registrationBuilder
import com.test.domain.models.response.RegistrationResponse
import com.test.domain.models.user.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RegistrationState(val name: String, val nickName: String, val phone: String)


internal class RegistrationViewModel(private val useCase: RegistrationUseCase, private val saveTokenUseCase: SaveRefreshTokenUseCase, private val saveUserUseCase: SaveUserUseCase) :
    ViewModel() {

    private val _errorState = MutableStateFlow(
        SendCodeError(
            msg = "",
            code = 0
        )
    )

    private val _uiState = MutableStateFlow(
        RegistrationState(
            name = "",
            nickName = "",
            phone = UserData.phone
        )
    )

    val uiState = _uiState.asStateFlow()
    val errorState = _errorState.asStateFlow()

    fun onErrorCodeChange(code: Int) {
        _errorState.update {
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

    fun onNicknameChange(nickname: String) {
        _uiState.update {
            it.copy(
                nickName = nickname
            )
        }
    }

    fun onNameChange(name: String) {
        _uiState.update {
            it.copy(
                name = name
            )
        }
    }

    private val _screen = MutableLiveData<NextScreen>()
    val screen: LiveData<NextScreen> = _screen


    fun registration(phone: String, name: String, username: String, onRegisterSuccess: () -> Unit) {
        val registration = registrationBuilder {
            this.phone = phone
            this.name = name
            this.username = username
        }

        viewModelScope.launch {
            when (val result = useCase.execute(registration)) {
                is RegistrationResponse.Error -> {
                    _errorState.update {
                        it.copy(
                            msg = result.message,
                            code = result.code
                        )
                    }
                }

                is RegistrationResponse.Registration -> {
                    UserData.accessToken = result.accessToken
                    saveTokenUseCase.execute(result.refreshToken)
                    val user = User(name = name, nickName = username, phone = phone)
                    saveUserUseCase.execute(user)
                    onRegisterSuccess()
                }
            }
        }
    }
}

internal class RegistrationViewModelFactory(private val registrationUseCase: RegistrationUseCase,
                                            private val saveTokenUseCase: SaveRefreshTokenUseCase,
                                            private val saveUserUseCase: SaveUserUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        return RegistrationViewModel(
            useCase = registrationUseCase,
            saveTokenUseCase = saveTokenUseCase,
            saveUserUseCase = saveUserUseCase
        ) as T
    }
}