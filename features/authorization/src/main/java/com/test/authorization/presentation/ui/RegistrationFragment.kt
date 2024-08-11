package com.test.authorization.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.test.authorization.di.AuthorizationFeatureDepsProvider
import com.test.authorization.di.DaggerAuthorizationComponent
import com.test.authorization.presentation.viewmodel.RegistrationViewModel
import com.test.authorization.presentation.viewmodel.RegistrationViewModelFactory
import com.test.authorization.presentation.navigation.registration.NextScreen
import com.test.chat_list_api.ChatListFeatureApi
import com.test.chatapp.authorization.R
import com.test.chatapp.authorization.databinding.FragmentRegistrationBinding
import com.test.data.temp.UserData
import com.test.domain.models.exception.IllegalArgumentNameException
import com.test.domain.models.exception.IllegalArgumentUsernameException
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
            try {
                viewModel.registration(
                    UserData.phone,
                    binding.etName.text.toString(),
                    binding.etNickname.text.toString()
                )
            }catch (e: IllegalArgumentNameException){
                showError(NextScreen.Error(getString(com.test.chatapp.core.ui.R.string.error_name)))

            }catch (e: IllegalArgumentUsernameException){
                showError(NextScreen.Error(getString(com.test.chatapp.core.ui.R.string.error_username)))
            }
        }

        viewModel.screen.observe(viewLifecycleOwner) {
            when(it){
                is NextScreen.ChatListFragment -> showChatListFragment()
                is NextScreen.Error -> showError(it)
                NextScreen.Nothing-> {}
            }

        }
    }

    private fun showError(error : NextScreen.Error){
        when(error.code){
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