package com.test.authorization.api

import androidx.fragment.app.Fragment
import com.test.authorization.presentation.ui.SendAuthCodeFragment
import com.test.authorization_api.AuthorizationFeatureApi
import javax.inject.Inject

class AuthorizationFeatureImpl @Inject constructor()  : AuthorizationFeatureApi {
    override fun open(): Fragment = SendAuthCodeFragment.getInstance()

}