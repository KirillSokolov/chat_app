package com.test.chatapp.presentation.navigation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.test.authorization_api.AuthorizationFeatureApi
import com.test.chat_list_api.ChatListFeatureApi
import com.test.chatapp.R
import com.test.chatapp.di.DaggerProvider
import com.test.data.api.AppDataPreference
import com.test.navigation.Router
import kotlinx.coroutines.launch
import javax.inject.Inject

class NavigatorFragment : Fragment(R.layout.fragment_navigator), NavigatorHolder {

    @Inject
    lateinit var navigatorLifecycle: NavigatorLifecycle

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var authorizationApi: AuthorizationFeatureApi

    @Inject
    lateinit var chatListFeatureApi: ChatListFeatureApi

    @Inject
    lateinit var dataStore: AppDataPreference

   // @Inject
   // lateinit var authorizationApi: AuthorizationApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerProvider.appComponent.inject(this)
        navigatorLifecycle.onCreate(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            openFragment()
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (childFragmentManager.backStackEntryCount > 0) {
                        childFragmentManager.popBackStack()
                    } else {
                        requireActivity().finish()
                    }
                }
            })
    }

    private fun openFragment(){
        lifecycleScope.launch {
            if (dataStore.getRefreshToken().isEmpty())
                router.navigateTo(authorizationApi.open())
            else
                router.navigateTo(chatListFeatureApi.open())
        }
    }

    override fun onDestroy() {
        navigatorLifecycle.onDestroy()
        super.onDestroy()
    }

    override fun manager(): FragmentManager = childFragmentManager

    override fun context(): Context = requireActivity()
}