package com.test.data.base

import com.test.data.api.database.MessagesDataSource
import com.test.domain.models.chat.Message
import javax.inject.Inject

class MessagesDataSourceImpl @Inject constructor(): MessagesDataSource {
    override suspend fun getMessages(): List<Message> {
        return emptyList()
    }
}