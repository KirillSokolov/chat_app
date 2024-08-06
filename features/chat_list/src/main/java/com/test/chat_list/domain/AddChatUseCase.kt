package com.test.chat_list.domain

import com.test.data.api.ChatListRepository
import javax.inject.Inject

class AddChatUseCase @Inject constructor(private val repository: ChatListRepository) {

    suspend fun execute(){
        repository.addChat()
    }
}