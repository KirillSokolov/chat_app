package com.test.authorization.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.authorization.domain.SendAuthCodeUseCase
import com.test.data.temp.UserData
import com.test.domain.models.request.SendAuthCode
import com.test.domain.models.response.SendAuthCodeResponse
import kotlinx.coroutines.launch

internal sealed class NextScreen {
    data object Nothing : NextScreen()
    class CheckAuthCodeFragment() : NextScreen()
    class ChatListFragment() : NextScreen()
    class RegistrationFragment() : NextScreen()
    class Error(val msg: String, val code: Int) : NextScreen()
}

internal class SendAuthCodeViewModel (private val useCase: SendAuthCodeUseCase) :
    ViewModel() {

    private val _screen = MutableLiveData<NextScreen>()
    val screen: LiveData<NextScreen> = _screen

    fun sendAuthCode(phone: String) {
        if(phone.isEmpty()){
            _screen.value = NextScreen.Error("",1)
            _screen.value = NextScreen.Nothing
        }else{
            viewModelScope.launch {
                when(val result = useCase.execute(SendAuthCode(phone = phone))){
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