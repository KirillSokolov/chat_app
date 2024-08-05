package com.test.chat_list.di

import com.test.chat_list.presentation.ChatListFragment
import dagger.Component

@Component(modules = [ChatListModule::class], dependencies = [ChatListFeatureDeps::class])
@ChatListScope
interface ChatListComponent {
    fun inject(fragment: ChatListFragment)

    @Component.Builder
    interface Builder{
        fun addDeps(deps:ChatListFeatureDeps): Builder
        fun build(): ChatListComponent
    }
}