package com.test.data.api.database

import com.test.domain.models.chat.Chat

interface ChatListDataSource {
    suspend fun getChat(): List<Chat>
}