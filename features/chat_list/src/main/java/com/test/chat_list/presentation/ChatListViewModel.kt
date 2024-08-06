package com.test.chat_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.chat_list.domain.AddChatUseCase
import com.test.chat_list.domain.GetAllChatUseCase
import com.test.domain.models.chat.Chat
import kotlinx.coroutines.launch

internal sealed class NextScreen {
    data object Nothing : NextScreen()
    class ChatFragment() : NextScreen()
    class UserFragment() : NextScreen()
}

internal class ChatListViewModel (private val addChatUseCase: AddChatUseCase, private val getAllChatUseCase: GetAllChatUseCase) :
    ViewModel() {

    private val userId = 0L;

    private val _chats = MutableLiveData<List<Chat>>(emptyList())
    val chats: LiveData<List<Chat>> = _chats

    private val _screen = MutableLiveData<NextScreen>()
    val screen: LiveData<NextScreen> = _screen

    fun load() {
        viewModelScope.launch {
            val chats = getAllChatUseCase.execute(userId)
            _chats.postValue(chats)
        }
    }

    fun addChatClicked() {
        viewModelScope.launch {
            addChatUseCase.execute()
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

class ChatListViewModelFactory(private val addChatUseCase: AddChatUseCase, private val getAllChatUseCase: GetAllChatUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        return ChatListViewModel(
            addChatUseCase = addChatUseCase,
            getAllChatUseCase = getAllChatUseCase
        ) as T
    }
}