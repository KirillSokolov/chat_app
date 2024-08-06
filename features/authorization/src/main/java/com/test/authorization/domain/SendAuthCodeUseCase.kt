package com.test.authorization.domain

import com.test.data.api.AuthorizationRepository
import com.test.domain.models.request.SendAuthCode
import com.test.domain.models.response.SendAuthCodeResponse
import javax.inject.Inject

internal class SendAuthCodeUseCase @Inject constructor(private val repository: AuthorizationRepository) {

    suspend fun execute(code: SendAuthCode): SendAuthCodeResponse {
        return repository.sendAuthCode(code)
    }
}