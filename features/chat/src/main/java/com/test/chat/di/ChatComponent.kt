package com.test.chat.di

import dagger.Component

@Component(modules = [ChatModule::class], dependencies = [ChatFeatureDeps::class])
@ChatScope
internal interface ChatComponent {

    @Component.Builder
    interface Builder{
        fun addDeps(deps:ChatFeatureDeps): Builder
        fun build(): ChatComponent
    }
}