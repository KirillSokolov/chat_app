package com.test.data.repository

import com.test.data.api.repository.ChatRepository
import com.test.data.api.database.MessagesDataSource
import com.test.domain.models.chat.Message
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(private val messagesDataSource: MessagesDataSource, private val dispatcher: CoroutineDispatcher) :
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