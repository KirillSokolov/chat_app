package com.test.chat_list.di

//import com.test.chat_api.ChatFeatureApi
import com.test.data.api.AppDataPreference
//import com.test.data.api.repository.ChatListRepository
import com.test.data.api.repository.UserRepository
import com.test.navigation.Router
//import com.test.user_details_api.UserDetailsFeatureApi

interface ChatListFeatureDeps {
   // val chatListRepository: ChatListRepository
 //   val chatFeatureApi: ChatFeatureApi
   // val userDetailsFeatureApi: UserDetailsFeatureApi
    val userRepository: UserRepository
    val preference: AppDataPreference
    val router: Router
}