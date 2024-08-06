package com.test.user.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.domain.models.user.User
import com.test.user.details.domain.EditUserUseCase
import kotlinx.coroutines.launch

internal class UserDetailsEditViewModel (private val editUserUseCase: EditUserUseCase) :
    ViewModel() {


    private val _user = MutableLiveData<User>(User())
    val user: LiveData<User> = _user

    private val _screen = MutableLiveData<NextScreen>()
    val screen: LiveData<NextScreen> = _screen

    fun load() {
        viewModelScope.launch {
           // val user = editUserUseCase.execute()
           // _user.postValue(user)
        }
    }

    fun backButtonClicked() {
        _screen.value = NextScreen.UserDetailsFragment()
        _screen.value = NextScreen.Nothing
    }
}

internal class UserDetailsEditViewModelFactory(private val editUserUseCase: EditUserUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        return UserDetailsEditViewModel(
            editUserUseCase = editUserUseCase
        ) as T
    }
}