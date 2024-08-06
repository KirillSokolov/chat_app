package com.test.chat_list.di

import com.test.chat_api.ChatFeatureApi
import com.test.data.api.ChatListRepository
import com.test.navigation.Router
import com.test.user_details_api.UserDetailsFeatureApi

interface ChatListFeatureDeps {
    val chatListRepository: ChatListRepository
    val chatFeatureApi: ChatFeatureApi
    val userDetailsFeatureApi: UserDetailsFeatureApi
    val router: Router
}