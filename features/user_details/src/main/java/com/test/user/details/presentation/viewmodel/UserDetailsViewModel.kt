package com.test.user.details.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.data.api.AppDataPreference
import com.test.data.temp.UserData
import com.test.domain.models.user.User
import com.test.user.details.domain.GetUserPhotoUseCase
import com.test.user.details.domain.GetUserUseCase
import com.test.user.details.presentation.navigation.getuser.NextScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UserUIState(
    val id: Long = 0L,
    val name: String = "",
    val phone: String = "",
    val nickName: String = "",
    val city: String = "",
    val birth: String = "",
    val zodiac : String = "",
    val aboutMe : String = "",
    val photoUri : String = "",
)

internal class UserDetailsViewModel (
    private val getUserUseCase: GetUserUseCase,
    private val getUserPhotoUseCase: GetUserPhotoUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow(
        UserUIState()
    )
    val uiState = _uiState.asStateFlow()

    private val _user = MutableLiveData<User>(User())
    val user: LiveData<User> = _user

    private val _screen = MutableLiveData<NextScreen>()
    val screen: LiveData<NextScreen> = _screen

    fun onPhotoChange(uri: Uri?) {
        uri?.let { photoUri ->
            viewModelScope.launch {
                getUserPhotoUseCase.execute(photoUri.toString())
            }
            _uiState.update {
                it.copy(
                    photoUri = photoUri.toString()
                )
            }
        }
    }

    fun load() {
        viewModelScope.launch {
            val photo = getUserPhotoUseCase.execute()
            val user = getUserUseCase.execute()
            _uiState.update {
                it.copy(
                    name = user.name,
                    nickName = user.nickName,
                    phone = UserData.phone,
                    aboutMe = user.aboutMe,
                    photoUri = photo
                )
            }
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

internal class UserDetailsViewModelFactory(private val getUserUseCase: GetUserUseCase,  private val getUserPhotoUseCase: GetUserPhotoUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        return UserDetailsViewModel(
            getUserUseCase = getUserUseCase,
            getUserPhotoUseCase = getUserPhotoUseCase
        ) as T
    }
}