package com.test.user.details.di

//import com.test.chat_list_api.ChatListFeatureApi
import com.test.data.api.repository.UserRepository
import com.test.navigation.Router

interface UserDetailsFeatureDeps {
    val userRepository: UserRepository
   // val chatListFeatureApi: ChatListFeatureApi
    val router: Router
}