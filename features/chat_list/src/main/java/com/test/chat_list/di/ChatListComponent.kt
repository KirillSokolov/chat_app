package com.test.chat_list.di

import com.test.chat_list.presentation.GetAccessTokenService
import com.test.chat_list.presentation.viewmodel.ChatListViewModelFactory
import dagger.Component

@Component(modules = [ChatListModule::class], dependencies = [ChatListFeatureDeps::class])
@ChatListScope
internal interface ChatListComponent {

    fun getChatListViewModelFactory(): ChatListViewModelFactory
    fun inject(fragment: GetAccessTokenService)
    @Component.Builder
    interface Builder{
        fun addDeps(deps:ChatListFeatureDeps): Builder
        fun build(): ChatListComponent
    }
}