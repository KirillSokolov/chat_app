package com.test.chatapp.presentation.navigation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.test.chatapp.R
import com.test.chatapp.di.DaggerProvider
import com.test.navigation.Router
import javax.inject.Inject

class NavigatorFragment : Fragment(R.layout.fragment_navigator), NavigatorHolder {

    @Inject
    lateinit var navigatorLifecycle: NavigatorLifecycle

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var newsFeatureApi: NewsFeatureApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerProvider.appComponent.inject(this)
        navigatorLifecycle.onCreate(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            router.navigateTo(newsFeatureApi.open())
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

    override fun onDestroy() {
        navigatorLifecycle.onDestroy()
        super.onDestroy()
    }

    override fun manager(): FragmentManager = childFragmentManager

    override fun context(): Context = requireActivity()
}