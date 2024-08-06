package com.test.authorization.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.test.authorization.di.AuthorizationFeatureDepsProvider
import com.test.authorization.di.DaggerAuthorizationComponent
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

        viewModel.screen.observe(viewLifecycleOwner) {

        }
    }

    private fun showChatListFragment() {
//        router.navigateTo(
//            fragment = articleDetailsFeatureApi.open(articleDetails),
//            addToBackStack = true
//        )
    }

    companion object {
        fun getInstance(): RegistrationFragment = RegistrationFragment()
    }
}