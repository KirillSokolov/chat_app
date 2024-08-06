package com.test.chat.di

import com.test.chat.domain.AddMessageUseCase
import com.test.chat.domain.GetAllMessagesUseCase
import com.test.chat.presentation.ChatViewModelFactory
import com.test.data.api.ChatRepository
import dagger.Module
import dagger.Provides

@Module
class ChatModule {

    @Provides
    fun providerChatViewModelFactory(getAllMessagesUseCase: GetAllMessagesUseCase, addMessageUseCase: AddMessageUseCase): ChatViewModelFactory {
        return ChatViewModelFactory(getAllMessagesUseCase = getAllMessagesUseCase, addMessageUseCase = addMessageUseCase)
    }

    @Provides
    fun providerGetAllMessagesUseCase(repository: ChatRepository): GetAllMessagesUseCase {
        return GetAllMessagesUseCase(repository = repository)
    }

    @Provides
    fun providerAddMessageUseCase(repository: ChatRepository): AddMessageUseCase {
        return AddMessageUseCase(repository = repository)
    }

}