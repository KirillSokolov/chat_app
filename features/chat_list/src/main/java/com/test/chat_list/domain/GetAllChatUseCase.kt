package com.test.chat_list.domain

import com.test.data.api.ChatListRepository
import com.test.domain.models.chat.Chat
import javax.inject.Inject

class GetAllChatUseCase @Inject constructor(private val repository: ChatListRepository) {

    suspend fun execute(userId:Long):List<Chat> {
       return repository.getAllChats(userId)
    }
}