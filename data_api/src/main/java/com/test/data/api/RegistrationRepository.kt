package com.test.data.api

import com.test.domain.models.request.SendAuthCode
import com.test.domain.models.response.Registration

interface RegistrationRepository {
   suspend fun registration(sendAuthCode: SendAuthCode): Registration
}