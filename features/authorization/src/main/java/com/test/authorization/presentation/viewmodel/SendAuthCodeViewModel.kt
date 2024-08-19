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
import kotlinx.coroutines.launch

internal class SendAuthCodeViewModel (private val useCase: SendAuthCodeUseCase) :
    ViewModel() {

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