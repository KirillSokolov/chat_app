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
import kotlinx.coroutines.launch

internal class RegistrationViewModel(private val useCase: RegistrationUseCase, private val saveTokenUseCase: SaveRefreshTokenUseCase, private val saveUserUseCase: SaveUserUseCase) :
    ViewModel() {

    private val _screen = MutableLiveData<NextScreen>()
    val screen: LiveData<NextScreen> = _screen


    fun registration(phone: String, name: String, username: String) {
        val registration = registrationBuilder {
            this.phone = phone
            this.name = name
            this.username = username
        }

        viewModelScope.launch {
            when (val result = useCase.execute(registration)) {
                is RegistrationResponse.Error -> {
                    _screen.value = NextScreen.Error(result.message, result.code)
                    _screen.value = NextScreen.Nothing
                }

                is RegistrationResponse.Registration -> {
                    UserData.accessToken = result.accessToken
                    saveTokenUseCase.execute(result.refreshToken)
                    val user = User(name = name, nickName = username, phone = phone)
                    saveUserUseCase.execute(user)
                    _screen.value = NextScreen.ChatListFragment()
                    _screen.value = NextScreen.Nothing
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