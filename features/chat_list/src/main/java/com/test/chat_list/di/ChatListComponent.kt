package com.test.chat_list.di

import dagger.Component

@Component(modules = [ChatListModule::class], dependencies = [ChatListFeatureDeps::class])
@ChatListScope
internal interface ChatListComponent {


    @Component.Builder
    interface Builder{
        fun addDeps(deps:ChatListFeatureDeps): Builder
        fun build(): ChatListComponent
    }
}