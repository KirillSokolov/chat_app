package com.test.chatapp.presentation.navigation

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.test.chatapp.R
import com.test.navigation.Router

class RouterImpl : Router, NavigatorLifecycle {

    private var navigatorHolder: NavigatorHolder? = null

    override fun onCreate(holder: NavigatorHolder) {
        navigatorHolder = holder
    }

    override fun onDestroy() {
        navigatorHolder = null
    }

    override fun navigateTo(fragment: Fragment, addToBackStack: Boolean) {
        val manager =
            navigatorHolder?.manager() ?: throw IllegalArgumentException("NavigationHolder is null")
        when {
            addToBackStack -> manager.commit {
                addToBackStack(null)
                replace(R.id.main_fragment_container, fragment)
            }

            else -> manager.commit {
                replace(R.id.main_fragment_container, fragment)
            }
        }
    }

    override fun navigateTo(url: String) {
        val context =
            navigatorHolder?.context() ?: throw IllegalArgumentException("NavigationHolder is null")
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }

    override fun back() {
        val manager =
            navigatorHolder?.manager() ?: throw IllegalArgumentException("NavigationHolder is null")
        manager.popBackStack()
    }


}