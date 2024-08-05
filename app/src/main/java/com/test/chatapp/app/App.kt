package com.test.chatapp.app

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initDagger()
    }

    private fun initDagger() {
        val appComponent = DaggerAppComponent
            .builder()
            .context(this)
            .build()
        DaggerProvider.appComponent = appComponent
        NewsFeatureDepsProvider.deps = appComponent
        ArticleDetailsFeatureDepsProvider.deps = appComponent
    }

}