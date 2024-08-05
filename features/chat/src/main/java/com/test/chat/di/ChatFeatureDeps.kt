package com.test.chat.di

import com.test.data_api.ChatRepository
import com.test.navigation.Router

interface ChatFeatureDeps {
    val repository: ChatRepository
    val router: Router
}