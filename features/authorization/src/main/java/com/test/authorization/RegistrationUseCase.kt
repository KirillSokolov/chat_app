package com.test.authorization

import com.test.data_api.RegistrationRepository
import com.test.domain.models.response.Registration

class RegistrationUseCase(private val repository: RegistrationRepository) {

    suspend fun execute(): Registration {
        return repository.registration()
    }

}