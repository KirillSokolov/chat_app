package com.test.authorization.presentation

import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.authorization.domain.RegistrationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel(private val useCase: RegistrationUseCase) :
    ViewModel() {


  //  private val _screen = MutableLiveData<NewsScreen>()
    //val screen: LiveData<NewsScreen> = _screen


    fun registration(phone: String) {
        viewModelScope.launch(Dispatchers.IO) {
            //useCase.execute()
           // _screen.value = NewsScreen.Details(article = articleDetails)
         //   _screen.value = NewsScreen.Nothing
        }


    }
}

class RegistrationViewModelFactory(private val registrationUseCase: RegistrationUseCase) :
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