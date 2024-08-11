package com.test.authorization.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.test.authorization.di.AuthorizationFeatureDepsProvider
import com.test.authorization.di.DaggerAuthorizationComponent
import com.test.authorization.presentation.viewmodel.CheckAuthCodeViewModel
import com.test.authorization.presentation.viewmodel.CheckAuthCodeViewModelFactory
import com.test.authorization.presentation.ui.RegistrationFragment
import com.test.authorization.presentation.navigation.checkcode.NextScreen
import com.test.chat_list_api.ChatListFeatureApi
import com.test.chatapp.authorization.R
import com.test.chatapp.authorization.databinding.FragmentCheckAuthCodeBinding
import com.test.data.temp.UserData
import com.test.domain.models.exception.IllegalArgumentCodeException
import com.test.navigation.Router
import javax.inject.Inject

internal class CheckAuthCodeFragment : Fragment(R.layout.fragment_check_auth_code) {

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
            try {
                viewModel.checkAuthCode(UserData.phone, binding.etEnterCode.text.toString())
            }catch (e: IllegalArgumentCodeException){
                showError(NextScreen.Error(getString(com.test.chatapp.core.ui.R.string.error_code)))
            }
        }

        binding.tvEnterCode.setText("${getString(com.test.chatapp.core.ui.R.string.enter_code)}: ${UserData.phone}")

        viewModel.screen.observe(viewLifecycleOwner) {
            when(it) {
                is NextScreen.ChatListFragment -> showChatListFragment()
                is NextScreen.Error -> showError(it)
                is NextScreen.RegistrationFragment -> showRegistrationFragment()
                NextScreen.Nothing -> {}
            }
        }
    }

    private fun showRegistrationFragment() {
        router.navigateTo(
            fragment = RegistrationFragment.getInstance(),
            addToBackStack = true
        )
    }

    private fun showChatListFragment() {
        router.navigateTo(
            fragment = chatListFeatureApi.open(),
            addToBackStack = true
        )
    }

    private fun showError(error : NextScreen.Error){
        when(error.code){
            else -> Toast.makeText(requireContext(), error.msg, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun getInstance(): CheckAuthCodeFragment = CheckAuthCodeFragment()
    }
}