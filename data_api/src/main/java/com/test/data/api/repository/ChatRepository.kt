package com.test.data.api.repository

import com.test.domain.models.chat.Message

interface ChatRepository {

    suspend fun getAllMessages(chatId: Long):List<Message>
    suspend fun addMessage()
    suspend fun deleteMessage()

}