package com.test.user.details.api

import androidx.fragment.app.Fragment
import com.test.user.details.presentation.UserDetailsFragment
import com.test.user_details_api.UserDetailsFeatureApi

import javax.inject.Inject

class UserDetailsFeatureImpl @Inject constructor(): UserDetailsFeatureApi {
    override fun open(): Fragment {
        return UserDetailsFragment.getInstance()
    }
}