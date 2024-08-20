package com.test.chat_list.presentation.viewmodel

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.test.chat_list.domain.GetBottomBarUseCase
import com.test.navigation.ChatTopLevelDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Immutable
data class BottomMenuUiState(
    val itemList: List<ChatTopLevelDestination>
)

internal class BottomMenuViewModel(
    private val getBottomBarUseCase: GetBottomBarUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        BottomMenuUiState(
            itemList = arrayListOf()
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getBottomBarUseCase.execute().let { items ->
                _uiState.update { currentState ->
                    currentState.copy(
                        itemList = items
                    )
                }
            }
        }
    }


    internal class Factory(
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            BottomMenuViewModel(
                getBottomBarUseCase = GetBottomBarUseCase()
            ) as T
    }
}