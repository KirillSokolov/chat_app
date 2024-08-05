package com.test.chat.di

import com.test.chat.domain.AddMessageUseCase
import com.test.chat.domain.GetAllMessagesUseCase
import com.test.data_api.ChatRepository
import dagger.Module
import dagger.Provides

@Module
class ChatModule {

    @Provides
    fun providerGetAllMessagesUseCase(repository: ChatRepository): GetAllMessagesUseCase {
        return GetAllMessagesUseCase(repository = repository)
    }

    @Provides
    fun providerAddMessageUseCase(repository: ChatRepository): AddMessageUseCase {
        return AddMessageUseCase(repository = repository)
    }

}