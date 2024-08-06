package com.test.user.details.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import com.test.navigation.Router
import com.test.user.details.R
import com.test.user.details.databinding.FragmentUserDetailsBinding
import com.test.user.details.databinding.FragmentUserDetailsEditBinding
import com.test.user.details.di.DaggerUserDetailsComponent
import com.test.user.details.di.UserDetailsFeatureDeps
import com.test.user.details.di.UserDetailsFeatureDepsProvider
import javax.inject.Inject

internal class UserDetailsEditFragment: Fragment(R.layout.fragment_user_details_edit) {

    @Inject
    lateinit var vmFactory: UserDetailsEditViewModelFactory

    @Inject
    lateinit var router: Router


    private val viewModel by viewModels<UserDetailsEditViewModel> {
        vmFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userComponent = DaggerUserDetailsComponent
            .builder()
            .addDeps(UserDetailsFeatureDepsProvider.deps)
            .build()

        userComponent.inject(this)

        if (savedInstanceState == null) {
            viewModel.load()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentUserDetailsEditBinding.bind(view)

        binding.btnBack.setOnClickListener{
            viewModel.backButtonClicked()
        }


        viewModel.user.observe(viewLifecycleOwner) {
        }

        viewModel.screen.observe(viewLifecycleOwner) {
             if (it is NextScreen.UserDetailsFragment)
               showUserDetailsFragment()
        }
    }

    private fun showUserDetailsFragment() {
        router.navigateTo(
            fragment = UserDetailsFragment.getInstance(),
            addToBackStack = true
        )
    }

    companion object {
        fun getInstance(): UserDetailsEditFragment = UserDetailsEditFragment()
    }
}