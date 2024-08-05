package com.test.chat.domain

import com.test.data_api.ChatRepository

class GetAllMessagesUseCase(private val repository: ChatRepository) {

    suspend fun execute(id: Long) {
        return repository.getChat(id)
    }
}