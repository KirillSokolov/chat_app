package com.test.chat.di

import com.test.chat.presentation.ChatFragment
import dagger.Component

@Component(modules = [ChatModule::class], dependencies = [ChatFeatureDeps::class])
@ChatScope
internal interface ChatComponent {
    fun inject(fragment: ChatFragment)

    @Component.Builder
    interface Builder{
        fun addDeps(deps:ChatFeatureDeps): Builder
        fun build(): ChatComponent
    }
}