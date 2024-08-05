package com.test.data_api

interface ChatRepository {

    fun getAllMessages(id: Long)
    fun addMessage()
    fun deleteMessage()

}