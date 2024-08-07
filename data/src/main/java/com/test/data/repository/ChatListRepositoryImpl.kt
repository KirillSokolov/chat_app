package com.test.data.repository

import com.test.data.api.repository.ChatListRepository
import com.test.data.api.database.ChatListDataSource
import com.test.domain.models.chat.Chat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatListRepositoryImpl @Inject constructor(private val chatListDataSource: ChatListDataSource, private val dispatcher: CoroutineDispatcher):
    ChatListRepository {

    override suspend fun getAllChats(): List<Chat> = withContext(dispatcher){
        return@withContext chatListDataSource.getChat()
    }

    override suspend fun addChat() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteChat() {
        TODO("Not yet implemented")
    }
}