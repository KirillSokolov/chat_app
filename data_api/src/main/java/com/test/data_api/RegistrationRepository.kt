package com.test.data_api

import com.test.domain.models.response.Registration

interface RegistrationRepository {
    fun registration(): Registration
}