package com.test.data.api.repository

import com.test.domain.models.chat.Chat

interface ChatListRepository {
    suspend fun getAllChats() : List<Chat>
    suspend fun addChat()
    suspend fun deleteChat()
}