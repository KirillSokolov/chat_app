package com.test.chatapp.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.test.chatapp.presentation.navigation.NavigatorLifecycle
import com.test.chatapp.presentation.navigation.RouterImpl
import com.test.data.preference.dataStore
import com.test.navigation.Router
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

private const val PREFERENCES_NAME = "chatapp_preferences"

val Application.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)


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

    @Provides
    @Singleton
    fun provideDataPreference(context: Context): DataStore<Preferences> {
       return (context as Application).dataStore
    }
}