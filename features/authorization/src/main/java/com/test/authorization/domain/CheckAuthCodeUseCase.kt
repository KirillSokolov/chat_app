package com.test.authorization.domain

import com.test.data.api.AuthorizationRepository
import com.test.domain.models.request.CheckAuthCode
import com.test.domain.models.response.CheckAuthCodeResponse
import javax.inject.Inject

internal class CheckAuthCodeUseCase @Inject constructor(private val repository: AuthorizationRepository) {

    suspend fun execute(code: CheckAuthCode): CheckAuthCodeResponse {
        return repository.checkAuthCode(code)
    }
}