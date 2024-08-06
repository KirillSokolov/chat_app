package com.test.authorization.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.test.authorization.di.AuthorizationFeatureDepsProvider
import com.test.authorization.di.DaggerAuthorizationComponent
import com.test.chatapp.authorization.R
import com.test.chatapp.authorization.databinding.FragmentSendAuthCodeBinding
import com.test.navigation.Router
import javax.inject.Inject

class SendAuthCodeFragment : Fragment(R.layout.fragment_send_auth_code) {

    @Inject
    lateinit var vmFactory: SendAuthCodeViewModelFactory

    @Inject
    lateinit var router: Router


    private val viewModel by viewModels<SendAuthCodeViewModel> {
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
        val binding = FragmentSendAuthCodeBinding.bind(view)
        binding.btnSend.setOnClickListener{
            viewModel.sendAuthCode(binding.etEnterPhone.text.toString())
        }

       viewModel.screen.observe(viewLifecycleOwner) {
            if (it is NextScreen.CheckAuthCodeFragment)
                showCheckAuthCodeFragment()
        }
    }

    private fun showCheckAuthCodeFragment() {
        router.navigateTo(
            fragment = CheckAuthCodeFragment.getInstance(),
            addToBackStack = true
        )
    }

    companion object {
        fun getInstance(): SendAuthCodeFragment = SendAuthCodeFragment()
    }
}