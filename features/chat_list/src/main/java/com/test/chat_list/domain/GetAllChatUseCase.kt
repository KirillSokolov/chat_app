package com.test.chat_list.domain

import com.test.data_api.ChatListRepository

class GetAllChatUseCase(private val repository: ChatListRepository) {

    suspend fun execute(userId:Long){
        repository.getAllChats(userId)
    }
}