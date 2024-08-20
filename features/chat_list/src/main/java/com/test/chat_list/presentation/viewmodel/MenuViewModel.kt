package com.test.chat_list.presentation.viewmodel

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.test.ui.BottomState.chatBottomMenu
import com.test.ui.MenuItem
import com.test.ui.MenuSection
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch



sealed interface MenuUiState {
    data object Loading : MenuUiState

    @Immutable
    data class Success(
        val sections: ImmutableList<MenuSection>,
        val selectedSectionIndex: Int = 0,
        val products: ImmutableList<MenuItem>
    ): MenuUiState{
        fun selectionTitle():String = sections[selectedSectionIndex].section
    }
}
class MenuViewModel(
) : ViewModel() {
    private val _uiState = MutableStateFlow<MenuUiState>(MenuUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        initMenu()
    }

    fun onSectionSelected(title: String) {
        viewModelScope.launch {
            val items = chatBottomMenu
            _uiState.update { currentState ->
                (currentState as MenuUiState.Success).copy(
                    selectedSectionIndex = currentState.sections.indexOfFirst {
                        it.section == title
                    },
                    products = items.toPersistentList()
                )
            }
        }
    }

    fun addToCart(item:MenuItem){
        viewModelScope.launch {
           // addToCartUseCase.execute(item)
        }
    }

    private fun initMenu(){
        viewModelScope.launch {
            val sections = arrayListOf<MenuSection>()
            sections.add(chatBottomMenu[0].section)
            sections.add(chatBottomMenu[1].section)
            val items = chatBottomMenu
            _uiState.update {_ ->
                MenuUiState.Success(
                    sections = sections.toPersistentList(),
                    selectedSectionIndex = 0,
                    products = items.toPersistentList()
                )
            }

        }
    }

    internal class Factory(

    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MenuViewModel() as T
    }

}