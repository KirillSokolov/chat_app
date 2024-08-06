package com.test.authorization.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.authorization.domain.RegistrationUseCase
import com.test.domain.models.request.Registration
import kotlinx.coroutines.launch

internal class RegistrationViewModel(private val useCase: RegistrationUseCase) :
    ViewModel() {


    private val _screen = MutableLiveData<NextScreen>()
    val screen: LiveData<NextScreen> = _screen


    fun registration(phone: String, name : String, username: String) {
        viewModelScope.launch {
            useCase.execute(Registration(phone = phone, name = name, username = username))
           // _screen.value = NewsScreen.Details(article = articleDetails)
         //   _screen.value = NewsScreen.Nothing
        }


    }
}

internal class RegistrationViewModelFactory(private val registrationUseCase: RegistrationUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        return RegistrationViewModel(
            useCase = registrationUseCase
        ) as T
    }
}