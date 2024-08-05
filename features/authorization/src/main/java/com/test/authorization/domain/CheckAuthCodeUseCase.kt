package com.test.authorization.domain

import com.test.data_api.AuthorizationRepository
import com.test.domain.models.request.CheckAuthCode
import com.test.domain.models.response.Authorization

class CheckAuthCodeUseCase (private val repository: AuthorizationRepository) {

    suspend fun execute(code: CheckAuthCode): Authorization {
        return repository.checkAuthCode(code)
    }
}