package com.test.user.details.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.test.chat_list_api.ChatListFeatureApi
import com.test.domain.models.user.User
import com.test.navigation.Router
import com.test.user.details.R
import com.test.user.details.databinding.FragmentUserDetailsBinding
import com.test.user.details.di.DaggerUserDetailsComponent
import com.test.user.details.di.UserDetailsFeatureDepsProvider
import com.test.user.details.presentation.navigation.getuser.NextScreen
import com.test.user.details.presentation.viewmodel.UserDetailsViewModel
import com.test.user.details.presentation.viewmodel.UserDetailsViewModelFactory
import javax.inject.Inject

internal class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {

    @Inject
    lateinit var vmFactory: UserDetailsViewModelFactory

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var chatListFeatureApi: ChatListFeatureApi

    private val viewModel by viewModels<UserDetailsViewModel> {
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
        val binding = FragmentUserDetailsBinding.bind(view)

        binding.btnBack.setOnClickListener{
            viewModel.backButtonClicked()
        }

        binding.btnEdit.setOnClickListener{
            viewModel.editButtonClicked()
        }

        viewModel.user.observe(viewLifecycleOwner) {
            it?.let { user->
                showUser(user, binding)
            }
        }

        viewModel.screen.observe(viewLifecycleOwner) {
            when(it){
                is NextScreen.ChatListFragment -> {
                    showChatListFragment()
                }
                is NextScreen.UserDetailsEditFragment -> {
                    showUserDetailsEditFragment()
                }
                NextScreen.Nothing -> {}
            }
        }
    }

    private fun showUser(user: User, binding: FragmentUserDetailsBinding) = with(binding){
        tvPhone.text = user.phone
        tvCity.text = user.city
        tvName.text = user.name
        tvBirth.text = user.birth
        tvZodiac.text = user.zodiac
    }

    private fun showChatListFragment() {
        router.navigateTo(
            fragment = chatListFeatureApi.open(),
            addToBackStack = true
        )
    }

    private fun showUserDetailsEditFragment() {
        router.navigateTo(
            fragment = UserDetailsEditFragment.getInstance(),
            addToBackStack = true
        )
    }

    companion object {
        fun getInstance(): UserDetailsFragment = UserDetailsFragment()
    }
}