package com.test.authorization.domain

import com.test.data.api.RegistrationRepository
import com.test.domain.models.request.SendAuthCode
import com.test.domain.models.response.Registration
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(private val repository: RegistrationRepository) {

    suspend fun execute(sendAuthCode: SendAuthCode): Registration {
        return repository.registration(sendAuthCode)
    }

}