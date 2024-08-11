package com.test.chat.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.chat.domain.AddMessageUseCase
import com.test.chat.domain.GetAllMessagesUseCase
import com.test.chat.presentation.navigation.NextScreen
import com.test.domain.models.chat.Message
import kotlinx.coroutines.launch

internal class ChatViewModel (private val getAllMessagesUseCase: GetAllMessagesUseCase, private val addMessageUseCase: AddMessageUseCase) :
    ViewModel() {

    private val chatId = 0L;

    private val _messages = MutableLiveData<List<Message>>(emptyList())
    val messages: LiveData<List<Message>> = _messages

    private val _screen = MutableLiveData<NextScreen>()
    val screen: LiveData<NextScreen> = _screen

    fun load() {
        viewModelScope.launch {
            val messages = getAllMessagesUseCase.execute(chatId)
            _messages.postValue(messages)
        }
    }

    fun backButtonClicked() {
        _screen.value = NextScreen.ChatListFragment()
        _screen.value = NextScreen.Nothing
    }
}

internal class ChatViewModelFactory(private val getAllMessagesUseCase: GetAllMessagesUseCase, private val addMessageUseCase: AddMessageUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        return ChatViewModel(
            getAllMessagesUseCase = getAllMessagesUseCase,
            addMessageUseCase = addMessageUseCase
        ) as T
    }
}