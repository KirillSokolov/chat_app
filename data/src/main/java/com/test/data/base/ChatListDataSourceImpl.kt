package com.test.data.base

import com.test.data.api.database.ChatListDataSource
import com.test.data.mappers.toDomain
import com.test.domain.models.chat.Chat
import javax.inject.Inject

class ChatListDataSourceImpl @Inject constructor() : ChatListDataSource{

    override suspend fun getChat(): List<Chat> {
        val chats = arrayListOf<Chat>()

        dataBaseChat.chatDbQueries.getAllChats().executeAsList().forEach{
            chats.add(it.toDomain())
        }

        return chats
    }
}