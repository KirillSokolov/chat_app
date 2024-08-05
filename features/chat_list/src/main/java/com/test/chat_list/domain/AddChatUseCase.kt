package com.test.chat_list.domain

import com.test.data_api.ChatListRepository

class AddChatUseCase (private val repository: ChatListRepository) {

    suspend fun execute(){
        repository.addChat()
    }
}