package com.test.chatapp.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun provideRouterImpl(): RouterImpl {
        return RouterImpl()
    }

    @Provides
    fun provideRouter(routerImpl: RouterImpl): Router {
        return routerImpl
    }

    @Provides
    fun provideNavigatorLifecycle(routerImpl: RouterImpl): NavigatorLifecycle {
        return routerImpl
    }
}