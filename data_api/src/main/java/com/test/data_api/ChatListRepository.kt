package com.test.data_api

interface ChatListRepository {
    fun getAllChats(userId: Long)
    fun addChat()
    fun deleteChat()
}