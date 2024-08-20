package com.test.user.details.presentation.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.domain.models.user.User
import com.test.user.details.domain.GetUserPhotoUseCase
import com.test.user.details.domain.GetUserUseCase
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
    val bitmap: Bitmap? = null,
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

    fun onPhotoChange(uri: String) {
        uri?.let { photoUri ->
            viewModelScope.launch {
                getUserPhotoUseCase.execute(photoUri)
            }
            _uiState.update {
                it.copy(
                    photoUri = photoUri,
                    bitmap = getBitmap(uri)
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
                    phone = user.phone,
                    aboutMe = user.aboutMe,
                    photoUri = Uri.parse(photo).toString(),
                    bitmap = getBitmap(photo)
                )
            }
        }
    }

    fun getBitmap(encodedImage: String):Bitmap{
        val decodedString: ByteArray = Base64.decode(encodedImage, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
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