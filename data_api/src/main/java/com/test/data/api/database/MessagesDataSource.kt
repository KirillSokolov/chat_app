package com.test.data.api.database

import com.test.domain.models.chat.Message

interface MessagesDataSource {
    suspend fun getMessages(): List<Message>
}