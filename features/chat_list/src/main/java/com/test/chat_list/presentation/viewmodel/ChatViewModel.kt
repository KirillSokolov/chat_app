package com.test.chat_list.presentation.viewmodel

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.domain.models.chat.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MessageUiState(
    val messages: ArrayList<Message>
)

class ChatViewModel(

) : ViewModel() {

    private val _chats = MutableStateFlow<MessageUiState>(MessageUiState(arrayListOf()))
    val chats= _chats.asStateFlow()

    private val _messageText = mutableStateOf("")
    val messageText: State<String> = _messageText

    fun onMessageChange(message: String) {
        _messageText.value = message
    }

    fun sendMessage() {
        viewModelScope.launch {
            if (messageText.value.isNotBlank()) {
                val messages = arrayListOf<Message>()
                messages.addAll(_chats.value.messages)
                messages.add(Message(text = _messageText.value, userId =  0L, sendTime = 0L))
                _chats.update {
                    it.copy(messages = messages)
                   // it.messages.
                }
                _messageText.value = ""
            }
        }
    }
}