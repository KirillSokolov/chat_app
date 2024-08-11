package com.test.chat_list.di

import com.test.chat_list.presentation.ui.ChatListFragment
import com.test.chat_list.presentation.GetAccessTokenService
import dagger.Component

@Component(modules = [ChatListModule::class], dependencies = [ChatListFeatureDeps::class])
@ChatListScope
internal interface ChatListComponent {
    fun inject(fragment: ChatListFragment)
    fun inject(fragment: GetAccessTokenService)

    @Component.Builder
    interface Builder{
        fun addDeps(deps:ChatListFeatureDeps): Builder
        fun build(): ChatListComponent
    }
}