package com.test.data.api

import com.test.domain.models.chat.Chat

interface ChatListRepository {
    suspend fun getAllChats(userId: Long) : List<Chat>
    suspend fun addChat()
    suspend fun deleteChat()
}