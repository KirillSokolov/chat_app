package com.test.chatapp.di

import com.test.data.api.AppDataPreference
import com.test.data.api.database.ChatListDataSource
import com.test.data.api.database.MessagesDataSource
import com.test.data.api.database.UserDataSource
import com.test.data.base.ChatListDataSourceImpl
import com.test.data.base.MessagesDataSourceImpl
import com.test.data.base.UserDataSourceImpl
import com.test.data.preference.AppDataPreferenceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataBaseModule {

    @Binds
    fun bindUserDataSource(repository: UserDataSourceImpl): UserDataSource

    @Binds
    fun bindChatListDataSource(repository: ChatListDataSourceImpl): ChatListDataSource

    @Binds
    fun bindCMessagesDataSource(repository: MessagesDataSourceImpl): MessagesDataSource

    @Binds
    fun bindSharedPreferences(appPreferenceImpl: AppDataPreferenceImpl): AppDataPreference
}