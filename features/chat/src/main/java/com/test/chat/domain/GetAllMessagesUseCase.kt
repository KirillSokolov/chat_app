package com.test.chat.domain

import com.test.data.api.repository.ChatRepository
import com.test.domain.models.chat.Message
import javax.inject.Inject

class GetAllMessagesUseCase @Inject constructor(private val repository: ChatRepository) {

    suspend fun execute(chatId: Long): List<Message> {
        return repository.getAllMessages(chatId)
    }
}