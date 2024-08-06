package com.test.authorization.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.authorization.domain.CheckAuthCodeUseCase
import com.test.domain.models.request.CheckAuthCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class CheckAuthCodeViewModel (private val useCase: CheckAuthCodeUseCase) :
    ViewModel() {



    private val _screen = MutableLiveData<NextScreen>()
    val screen: LiveData<NextScreen> = _screen


    fun checkAuthCode(phone:String, code: String ) {
      //  viewModelScope.launch(Dispatchers.IO) {
         //   useCase.execute(CheckAuthCode(phone = phone, code = code))
            _screen.value = NextScreen.ChatListFragment()
            _screen.value = NextScreen.Nothing
      //  }

    }
}

class CheckAuthCodeViewModelFactory(private val checkAuthCodeUseCase: CheckAuthCodeUseCase) :
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