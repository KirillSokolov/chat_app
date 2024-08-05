package com.test.authorization.domain

import com.test.data_api.AuthorizationRepository
import com.test.domain.models.request.SendAuthCode

class SendAuthCodeUseCase(private val repository: AuthorizationRepository) {

    suspend fun execute(code: SendAuthCode): Boolean {
        return repository.sendAuthCode(code)
    }
}