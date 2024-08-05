package com.test.authorization.di

import com.test.data_api.AuthorizationRepository
import com.test.data_api.RegistrationRepository
import com.test.navigation.Router

interface AuthorizationFeatureDeps {
    val authorizationRepository: AuthorizationRepository
    val articleDetailsFeatureApi: RegistrationRepository
    val router: Router
}