package com.test.data_api

import com.test.domain_models.Registration

interface RegistrationRepository {
    fun registration():Registration
}