package com.test.chat_list.di

import com.test.chat_list.domain.AddChatUseCase
import com.test.chat_list.domain.GetAllChatUseCase
import com.test.chat_list.presentation.viewmodel.ChatListViewModelFactory
import com.test.data.api.repository.ChatListRepository
import dagger.Module
import dagger.Provides

@Module
internal class ChatListModule {

    @Provides
    fun providerChatListViewModelFactory(addChatUseCase: AddChatUseCase, getAllChatUseCase: GetAllChatUseCase): ChatListViewModelFactory {
        return ChatListViewModelFactory(addChatUseCase = addChatUseCase, getAllChatUseCase = getAllChatUseCase)
    }

    @Provides
    fun providerGetAllChatUseCase(repository: ChatListRepository): GetAllChatUseCase {
        return GetAllChatUseCase(repository = repository)
    }

    @Provides
    fun providerAddChatUseCase(repository: ChatListRepository): AddChatUseCase {
        return AddChatUseCase(repository = repository)
    }
}