package com.test.chat_list.di

import com.test.chat_list.domain.AddChatUseCase
import com.test.chat_list.domain.GetAllChatUseCase
import com.test.data_api.ChatListRepository
import dagger.Module
import dagger.Provides

@Module
class ChatListModule {

    @Provides
    fun providerGetAllChatUseCase(repository: ChatListRepository): GetAllChatUseCase {
        return GetAllChatUseCase(repository = repository)
    }

    @Provides
    fun providerAddChatUseCase(repository: ChatListRepository): AddChatUseCase {
        return AddChatUseCase(repository = repository)
    }
}