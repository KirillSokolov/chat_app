package com.test.authorization.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.authorization.domain.CheckAuthCodeUseCase
import com.test.domain.models.request.CheckAuthCode
import com.test.domain.models.request.checkAuthCodeBuilder
import com.test.domain.models.request.sendAuthCodeBuilder
import com.test.domain.models.response.CheckAuthCodeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class CheckAuthCodeViewModel (private val useCase: CheckAuthCodeUseCase) :
    ViewModel() {

    private val _screen = MutableLiveData<NextScreen>()
    val screen: LiveData<NextScreen> = _screen

    fun checkAuthCode(phone: String, code: String) {
        val checkAuthCode = checkAuthCodeBuilder {
            this.phone = phone
            this.code = code
        }
        viewModelScope.launch {
            when (val result = useCase.execute(checkAuthCode)) {
                is CheckAuthCodeResponse.Error -> {
                    _screen.value = NextScreen.Error(msg = result.message, code = result.code)
                    _screen.value = NextScreen.Nothing
                }

                is CheckAuthCodeResponse.Verification -> {
                    checkExistUser(result)
                }
            }
        }
    }

    private fun checkExistUser(verification: CheckAuthCodeResponse.Verification) {
        if (verification.isUserExists) {
            _screen.value = NextScreen.ChatListFragment()
            _screen.value = NextScreen.Nothing
        }else{
            _screen.value = NextScreen.RegistrationFragment()
            _screen.value = NextScreen.Nothing
        }
    }
}


internal class CheckAuthCodeViewModelFactory(private val checkAuthCodeUseCase: CheckAuthCodeUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        return CheckAuthCodeViewModel(
            useCase = checkAuthCodeUseCase
        ) as T
    }
}