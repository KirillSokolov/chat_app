package com.test.authorization.domain

import com.test.data.api.AuthorizationRepository
import com.test.domain.models.request.SendAuthCode
import javax.inject.Inject

class SendAuthCodeUseCase @Inject constructor(private val repository: AuthorizationRepository) {

    suspend fun execute(code: SendAuthCode): Boolean {
        return repository.sendAuthCode(code)
    }
}