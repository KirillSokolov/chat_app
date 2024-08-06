package com.test.chat.di

import com.test.chat_list_api.ChatListFeatureApi
import com.test.data.api.ChatRepository
import com.test.navigation.Router

interface ChatFeatureDeps {
    val chatRepository: ChatRepository
    val chatListFeatureApi: ChatListFeatureApi
    val router: Router
}