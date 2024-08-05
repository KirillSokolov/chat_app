package com.test.chatapp.di

import dagger.Binds
import dagger.Module

@Module
interface RepositoriesModule {
    @Binds
    fun bindArticleRepository(repository: ChatRepositoryImpl): ChatRepository
}