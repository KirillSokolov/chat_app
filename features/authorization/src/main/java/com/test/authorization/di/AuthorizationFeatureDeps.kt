package com.test.authorization.di

import com.test.chat_list_api.ChatListFeatureApi
import com.test.data.api.AppDataPreference
import com.test.data.api.repository.AuthorizationRepository
import com.test.data.api.repository.RegistrationRepository
import com.test.data.api.repository.UserRepository
import com.test.navigation.Router

interface AuthorizationFeatureDeps {
    val authorizationRepository: AuthorizationRepository
    val registrationRepository: RegistrationRepository
    val chatListFeatureApi: ChatListFeatureApi
    val userRepository: UserRepository
    val preference: AppDataPreference
    val router: Router
}