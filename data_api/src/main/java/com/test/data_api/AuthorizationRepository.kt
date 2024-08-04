package com.test.data_api

import com.test.domain.models.request.CheckAuthCode
import com.test.domain.models.request.SendAuthCode
import com.test.domain.models.response.Authorization

interface AuthorizationRepository {
    suspend fun authorization(checkAuthCode: CheckAuthCode): Authorization
    suspend fun authCode(sendAuthCode: SendAuthCode):Boolean
}