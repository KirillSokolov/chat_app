package com.test.chat_list.domain

import com.test.data.api.repository.ChatListRepository
import javax.inject.Inject

internal class AddChatUseCase @Inject constructor(private val repository: ChatListRepository) {

    suspend fun execute(){
        repository.addChat()
    }
}