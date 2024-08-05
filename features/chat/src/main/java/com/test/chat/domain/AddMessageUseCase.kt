package com.test.chat.domain

import com.test.data_api.ChatRepository

class AddMessageUseCase(private val repository: ChatRepository) {

    suspend fun execute() {
        repository.addMessage()
    }
}