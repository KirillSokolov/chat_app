package com.test.data.repository

import com.test.data.api.ChatListRepository
import com.test.domain.models.chat.Chat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatListRepositoryImpl @Inject constructor(private val dispatcher: CoroutineDispatcher):
    ChatListRepository {

    override suspend fun getAllChats(userId: Long): List<Chat> = withContext(dispatcher){
        return@withContext emptyList()
    }

    override suspend fun addChat() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteChat() {
        TODO("Not yet implemented")
    }
}