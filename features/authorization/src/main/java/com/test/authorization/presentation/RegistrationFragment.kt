package com.test.authorization.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.test.authorization.di.AuthorizationFeatureDepsProvider
import com.test.authorization.di.DaggerAuthorizationComponent
import com.test.chat_list_api.ChatListFeatureApi
import com.test.chatapp.authorization.R
import com.test.chatapp.authorization.databinding.FragmentRegistrationBinding
import com.test.data.temp.UserData
import com.test.navigation.Router
import javax.inject.Inject

internal class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    @Inject
    lateinit var vmFactory: RegistrationViewModelFactory

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var chatListFeatureApi: ChatListFeatureApi


    private val viewModel by viewModels<RegistrationViewModel> {
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
        val binding = FragmentRegistrationBinding.bind(view)
        binding.tvPhone.text = UserData.phone

        binding.btnRegister.setOnClickListener{
            viewModel.registration(UserData.phone, binding.etName.text.toString(), binding.etNickname.text.toString())
        }

        viewModel.screen.observe(viewLifecycleOwner) {
            when(it){
                is NextScreen.ChatListFragment -> showChatListFragment()
                is NextScreen.Error -> showError(it)
                NextScreen.Nothing, is NextScreen.CheckAuthCodeFragment , is NextScreen.RegistrationFragment -> {}
            }

        }
    }

    private fun showError(error : NextScreen.Error){
        when(error.code){
            1 -> Toast.makeText(requireContext(), getString(com.test.chatapp.core.ui.R.string.error_code), Toast.LENGTH_SHORT).show()
            2 -> Toast.makeText(requireContext(), getString(com.test.chatapp.core.ui.R.string.error_name), Toast.LENGTH_SHORT).show()
            3 -> Toast.makeText(requireContext(), getString(com.test.chatapp.core.ui.R.string.error_nickname), Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(requireContext(), error.msg, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showChatListFragment() {
        router.navigateTo(
            fragment = chatListFeatureApi.open(),
            addToBackStack = true
        )
    }

    companion object {
        fun getInstance(): RegistrationFragment = RegistrationFragment()
    }
}