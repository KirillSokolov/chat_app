package com.test.chatapp.di

import com.test.authorization.api.AuthorizationFeatureImpl
import com.test.authorization_api.AuthorizationFeatureApi
import com.test.chat.api.ChatFeatureImpl
import com.test.user.details.api.UserDetailsFeatureImpl
import com.test.chat_api.ChatFeatureApi
import com.test.chat_list.api.ChatListFeatureImpl
import com.test.chat_list_api.ChatListFeatureApi
import com.test.user_details_api.UserDetailsFeatureApi
import dagger.Binds
import dagger.Module

@Module
interface FeaturesModule {
    @Binds
    fun bindChatFeature(featureApi: ChatFeatureImpl): ChatFeatureApi

    @Binds
    fun bindChatListFeature(featureApi: ChatListFeatureImpl): ChatListFeatureApi

    @Binds
    fun bindAuthorizationFeature(featureApi: AuthorizationFeatureImpl): AuthorizationFeatureApi

    @Binds
    fun bindUserDetailsFeature(featureApi: UserDetailsFeatureImpl): UserDetailsFeatureApi

}
