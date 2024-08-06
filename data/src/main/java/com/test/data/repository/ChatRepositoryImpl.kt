package com.test.data.repository

import com.test.data.api.ChatRepository
import com.test.domain.models.chat.Message
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(private val dispatcher: CoroutineDispatcher) :
    ChatRepository {
    override suspend fun getAllMessages(chatId: Long): List<Message> = withContext(dispatcher) {
        return@withContext emptyList()
    }

    override suspend fun addMessage() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMessage() {
        TODO("Not yet implemented")
    }
}