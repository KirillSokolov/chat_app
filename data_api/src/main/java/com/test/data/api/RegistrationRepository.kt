package com.test.data.api

import com.test.domain.models.request.Registration
import com.test.domain.models.response.RegistrationResponse

interface RegistrationRepository {

   suspend fun registration(registration: Registration): RegistrationResponse
}