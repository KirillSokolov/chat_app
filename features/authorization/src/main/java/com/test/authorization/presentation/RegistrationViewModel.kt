package com.test.authorization.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.authorization.domain.RegistrationUseCase
import com.test.authorization.domain.SaveRefreshTokenUseCase
import com.test.authorization.domain.SaveUserUseCase
import com.test.data.temp.UserData
import com.test.domain.models.request.Registration
import com.test.domain.models.response.RegistrationResponse
import com.test.domain.models.user.User
import kotlinx.coroutines.launch

internal class RegistrationViewModel(private val useCase: RegistrationUseCase, private val saveTokenUseCase: SaveRefreshTokenUseCase, private val saveUserUseCase: SaveUserUseCase) :
    ViewModel() {

    private val _screen = MutableLiveData<NextScreen>()
    val screen: LiveData<NextScreen> = _screen


    fun registration(phone: String, name : String, username: String) {
        if(name.isEmpty()){
            _screen.value = NextScreen.Error("", 2)
            _screen.value = NextScreen.Nothing
        }else if(username.isEmpty()){
            _screen.value = NextScreen.Error("", 3)
            _screen.value = NextScreen.Nothing
        }else {


            viewModelScope.launch {
                when (val result = useCase.execute(
                    Registration(
                        phone = phone,
                        name = name,
                        username = username
                    )
                )) {
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