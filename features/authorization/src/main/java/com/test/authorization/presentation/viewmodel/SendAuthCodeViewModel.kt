package com.test.authorization.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.arpitkatiyarprojects.countrypicker.models.CountryDetails
import com.test.authorization.domain.SendAuthCodeUseCase
import com.test.authorization.presentation.navigation.sendcode.NextScreen
import com.test.data.temp.UserData
import com.test.domain.models.request.sendAuthCodeBuilder
import com.test.domain.models.response.SendAuthCodeResponse
import com.test.ui.widgets.inputPhoneType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SendCodeState(
    val code: String,
    val phone: TextFieldValue
)

data class SendCodeError(
    val code: Int,
    val msg: String
)


internal class SendAuthCodeViewModel (private val useCase: SendAuthCodeUseCase) :
    ViewModel() {

    private val _errorState = MutableStateFlow(
        SendCodeError(
            msg = "",
            code = 0
        )
    )

    private val _uiState = MutableStateFlow(
        SendCodeState(
            phone = TextFieldValue(
                text = "",
                selection = TextRange(0)
            ),
            code = ""
        )
    )

    val uiState = _uiState.asStateFlow()
    val errorState = _errorState.asStateFlow()

    fun onErrorCodeChange(code: Int) {
        _errorState.update {
            it.copy(
                code = code
            )
        }
    }


    fun onCodeChange(country: CountryDetails) {
        _uiState.update {
            it.copy(
                code = country.countryPhoneNumberCode
            )
        }
    }

    fun onPhoneChange(phone: TextFieldValue) {
        val tx = inputPhoneType(phone)
        UserData.phone = tx.text

        _uiState.update {
            it.copy(
                phone = tx
            )
        }
    }

    fun sendAuthCode(code: String, phone: String, onSignedIn: () -> Unit) {
        onErrorCodeChange(0)
        val sendAuthCode = sendAuthCodeBuilder {
            this.phone = phone
            this.code = code
        }

        viewModelScope.launch {
            when(val result = useCase.execute(sendAuthCode)){
                is SendAuthCodeResponse.Authorization -> {
                    UserData.phone = sendAuthCode.phone
                    onSignedIn()
                }
                is SendAuthCodeResponse.Error -> {
                    _errorState.update {
                        it.copy(
                            msg = result.message,
                            code = System.currentTimeMillis().toInt()
                        )
                    }
                   // _screen.value = NextScreen.Error(msg = result.message, code = result.code)
                  //  _screen.value = NextScreen.Nothing
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