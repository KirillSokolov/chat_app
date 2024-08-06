package com.test.chatapp.di

import com.test.data.repository.AuthorizationRepositoryImpl
import com.test.data.repository.ChatListRepositoryImpl
import com.test.data.repository.ChatRepositoryImpl
import com.test.data.repository.RegistrationRepositoryImpl
import com.test.data.repository.UserRepositoryImpl
import com.test.data.api.AuthorizationRepository
import com.test.data.api.ChatListRepository
import com.test.data.api.ChatRepository
import com.test.data.api.RegistrationRepository
import com.test.data.api.UserRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoriesModule {
    @Binds
    fun bindChatRepository(repository: ChatRepositoryImpl): ChatRepository

    @Binds
    fun bindChatListRepository(repository: ChatListRepositoryImpl): ChatListRepository

    @Binds
    fun bindRegistrationRepository(repository: RegistrationRepositoryImpl): RegistrationRepository

    @Binds
    fun bindAuthorizationRepository(repository: AuthorizationRepositoryImpl): AuthorizationRepository

    @Binds
    fun bindUserRepository(repository: UserRepositoryImpl): UserRepository
}