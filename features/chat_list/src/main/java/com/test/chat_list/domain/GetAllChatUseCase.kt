package com.test.chat_list.domain

import com.test.data.api.repository.ChatListRepository
import com.test.domain.models.chat.Chat
import javax.inject.Inject

internal class GetAllChatUseCase @Inject constructor(private val repository: ChatListRepository) {

    suspend fun execute():List<Chat> {
       return repository.getAllChats()
    }
}