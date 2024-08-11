package com.test.user.details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.domain.models.user.User
import com.test.user.details.domain.GetUserUseCase
import com.test.user.details.presentation.navigation.getuser.NextScreen
import kotlinx.coroutines.launch



internal class UserDetailsViewModel (private val getUserUseCase: GetUserUseCase) :
    ViewModel() {

    private val _user = MutableLiveData<User>(User())
    val user: LiveData<User> = _user

    private val _screen = MutableLiveData<NextScreen>()
    val screen: LiveData<NextScreen> = _screen

    fun load() {
        viewModelScope.launch {
            val user = getUserUseCase.execute()
            _user.postValue(user)
        }
    }

    fun editButtonClicked() {
        _screen.value = NextScreen.UserDetailsEditFragment()
        _screen.value = NextScreen.Nothing
    }

    fun backButtonClicked() {
        _screen.value = NextScreen.ChatListFragment()
        _screen.value = NextScreen.Nothing
    }
}

internal class UserDetailsViewModelFactory(private val getUserUseCase: GetUserUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        return UserDetailsViewModel(
            getUserUseCase = getUserUseCase
        ) as T
    }
}