package com.test.authorization.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.test.authorization.di.AuthorizationFeatureDepsProvider
import com.test.authorization.di.DaggerAuthorizationComponent
import com.test.chat_list_api.ChatListFeatureApi
import com.test.chatapp.authorization.R
import com.test.chatapp.authorization.databinding.FragmentCheckAuthCodeBinding
import com.test.data.temp.UserData
import com.test.navigation.Router
import javax.inject.Inject

class CheckAuthCodeFragment : Fragment(R.layout.fragment_check_auth_code) {

    @Inject
    lateinit var vmFactory: CheckAuthCodeViewModelFactory

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var chatListFeatureApi: ChatListFeatureApi

    private val viewModel by viewModels<CheckAuthCodeViewModel> {
        vmFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val authorizationComponent = DaggerAuthorizationComponent
            .builder()
            .addDeps(AuthorizationFeatureDepsProvider.deps)
            .build()

        authorizationComponent.inject(this)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCheckAuthCodeBinding.bind(view)
        binding.btnVerify.setOnClickListener{
            viewModel.checkAuthCode(UserData.phone, binding.etEnterCode.text.toString())
        }

        viewModel.screen.observe(viewLifecycleOwner) {
            if (it is NextScreen.ChatListFragment)
                showChatListFragment()
        }
    }

    private fun showChatListFragment() {
        router.navigateTo(
            fragment = chatListFeatureApi.open(),
            addToBackStack = true
        )
    }

    companion object {
        fun getInstance(): CheckAuthCodeFragment = CheckAuthCodeFragment()
    }
}