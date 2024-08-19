package com.test.chatapp.presentation.navigation

import android.content.Intent
import android.net.Uri

import com.test.navigation.Router

class RouterImpl : Router, NavigatorLifecycle {

    private var navigatorHolder: NavigatorHolder? = null

    override fun onCreate(holder: NavigatorHolder) {
        navigatorHolder = holder
    }

    override fun onDestroy() {
        navigatorHolder = null
    }

    override fun navigateTo(url: String) {
        val context =
            navigatorHolder?.context() ?: throw IllegalArgumentException("NavigationHolder is null")
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }

}