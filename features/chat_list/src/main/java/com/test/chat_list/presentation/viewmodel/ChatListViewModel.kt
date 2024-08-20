package com.test.chat_list.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.chat_list.domain.AddChatUseCase
import com.test.chat_list.domain.GetAllChatUseCase
import com.test.chat_list.presentation.navigation.NextScreen
import com.test.domain.models.chat.Chat
import kotlinx.coroutines.launch
import javax.inject.Inject


internal class ChatListViewModel (private val getAllChatUseCase: GetAllChatUseCase) :
    ViewModel() {


    private val _chats = MutableLiveData<List<Chat>>(emptyList())
    val chats: LiveData<List<Chat>> = _chats

    private val _screen = MutableLiveData<NextScreen>()
    val screen: LiveData<NextScreen> = _screen

    fun load() {
        viewModelScope.launch {
            val chats = getAllChatUseCase.execute()
            _chats.postValue(chats)
        }
    }

    fun addChatClicked() {
        viewModelScope.launch {
           // addChatUseCase.execute()
        }
    }

    fun userClicked() {
        _screen.value = NextScreen.UserFragment()
        _screen.value = NextScreen.Nothing
    }

    fun chatClicked(id: Long) {
        _screen.value = NextScreen.ChatFragment()
        _screen.value = NextScreen.Nothing
    }
}

internal class ChatListViewModelFactory @Inject constructor(private val getAllChatUseCase: GetAllChatUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        return ChatListViewModel(
            getAllChatUseCase = getAllChatUseCase
        ) as T
    }
}