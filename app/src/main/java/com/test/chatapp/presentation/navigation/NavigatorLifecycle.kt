package com.test.chatapp.presentation.navigation

interface NavigatorLifecycle {
    fun onCreate(holder: NavigatorHolder)
    fun onDestroy()
}