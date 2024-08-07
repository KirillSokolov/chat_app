package com.test.data.api.repository

import com.test.domain.models.request.CheckAuthCode
import com.test.domain.models.request.SendAuthCode
import com.test.domain.models.response.CheckAuthCodeResponse
import com.test.domain.models.response.SendAuthCodeResponse

interface AuthorizationRepository {
    suspend fun checkAuthCode(checkAuthCode: CheckAuthCode): CheckAuthCodeResponse
    suspend fun sendAuthCode(sendAuthCode: SendAuthCode): SendAuthCodeResponse
}