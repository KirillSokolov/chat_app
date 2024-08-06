package com.test.chat.domain

import com.test.data.api.ChatRepository
import javax.inject.Inject

class AddMessageUseCase @Inject constructor(private val repository: ChatRepository) {

    suspend fun execute() {
        repository.addMessage()
    }
}