package com.test.authorization.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.authorization.domain.SendAuthCodeUseCase
import com.test.domain.models.request.SendAuthCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal sealed class NextScreen {
    data object Nothing : NextScreen()
    class CheckAuthCodeFragment() : NextScreen()
    class ChatListFragment() : NextScreen()
}

internal class SendAuthCodeViewModel (private val useCase: SendAuthCodeUseCase) :
    ViewModel() {


    private val _screen = MutableLiveData<NextScreen>()
    val screen: LiveData<NextScreen> = _screen


    fun sendAuthCode(phone: String) {
        if(phone.isEmpty()){

        }else{
          //  viewModelScope.launch(Dispatchers.IO) {
                //useCase.execute(SendAuthCode(phone = phone))
                 _screen.value = NextScreen.CheckAuthCodeFragment()
                 _screen.value = NextScreen.Nothing
          //  }

        }


    }
}

class SendAuthCodeViewModelFactory(private val sendAuthCodeUseCase: SendAuthCodeUseCase) :
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