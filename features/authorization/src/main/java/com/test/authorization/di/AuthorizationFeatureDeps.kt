package com.test.authorization.di

import com.test.chat_list_api.ChatListFeatureApi
import com.test.data.api.AuthorizationRepository
import com.test.data.api.RegistrationRepository
import com.test.navigation.Router

interface AuthorizationFeatureDeps {
    val authorizationRepository: AuthorizationRepository
    val registrationRepository: RegistrationRepository
    val chatListFeatureApi: ChatListFeatureApi

    val router: Router
}