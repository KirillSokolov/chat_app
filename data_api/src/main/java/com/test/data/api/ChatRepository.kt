package com.test.data.api

import com.test.domain.models.chat.Message

interface ChatRepository {

    suspend fun getAllMessages(chatId: Long):List<Message>
    suspend fun addMessage()
    suspend fun deleteMessage()

}