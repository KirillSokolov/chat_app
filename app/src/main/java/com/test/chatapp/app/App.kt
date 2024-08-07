package com.test.chatapp.app

import android.app.Application
import com.test.authorization.di.AuthorizationFeatureDepsProvider
import com.test.chat.di.ChatFeatureDepsProvider
import com.test.chat_list.di.ChatListFeatureDepsProvider
import com.test.chatapp.di.DaggerAppComponent
import com.test.chatapp.di.DaggerProvider
import com.test.data.base.createDBDriver
import com.test.data.preference.createDataStore
import com.test.user.details.di.UserDetailsFeatureDepsProvider

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initDagger()
        createDBDriver(this)
        createDataStore(this)
    }

    private fun initDagger() {
        val appComponent = DaggerAppComponent
            .builder()
            .context(this)
            .build()
        DaggerProvider.appComponent = appComponent
        AuthorizationFeatureDepsProvider.deps = appComponent
        ChatFeatureDepsProvider.deps = appComponent
        ChatListFeatureDepsProvider.deps = appComponent
        UserDetailsFeatureDepsProvider.deps = appComponent
    }

}