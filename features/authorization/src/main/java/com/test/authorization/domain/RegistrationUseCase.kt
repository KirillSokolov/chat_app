package com.test.authorization.domain

import com.test.data.api.repository.RegistrationRepository
import com.test.domain.models.request.Registration
import com.test.domain.models.response.RegistrationResponse
import javax.inject.Inject

internal class RegistrationUseCase @Inject constructor(private val repository: RegistrationRepository) {

    suspend fun execute(registration: Registration): RegistrationResponse {
        return repository.registration(registration)
    }

}