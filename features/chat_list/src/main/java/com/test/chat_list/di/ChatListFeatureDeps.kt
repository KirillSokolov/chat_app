package com.test.chat_list.di

import com.test.data_api.ChatListRepository
import com.test.navigation.Router

interface ChatListFeatureDeps {
    val repository: ChatListRepository
    val router: Router
}