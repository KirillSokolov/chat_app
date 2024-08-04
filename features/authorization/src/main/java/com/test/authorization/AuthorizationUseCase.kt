package com.test.authorization

import com.test.data_api.AuthorizationRepository
import com.test.domain.models.response.Authorization

class AuthorizationUseCase(private val repository: AuthorizationRepository) {

    suspend fun execute(): Authorization {
        return repository.authorization()
    }
}