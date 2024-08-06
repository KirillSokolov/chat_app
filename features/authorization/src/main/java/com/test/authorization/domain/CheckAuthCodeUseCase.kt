package com.test.authorization.domain

import com.test.data.api.AuthorizationRepository
import com.test.domain.models.request.CheckAuthCode
import com.test.domain.models.response.Authorization
import javax.inject.Inject

class CheckAuthCodeUseCase @Inject constructor(private val repository: AuthorizationRepository) {

    suspend fun execute(code: CheckAuthCode): Authorization {
        return repository.checkAuthCode(code)
    }
}