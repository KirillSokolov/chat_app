package com.test.data.api.repository

import com.test.domain.models.request.RefreshToken
import com.test.domain.models.user.User

interface UserRepository {
    suspend fun getUser() : User
    suspend fun updateLocalUser(user: User)
    suspend fun updateGlobalUser(user: User)
    suspend fun getAccessToken(refreshToken: RefreshToken)
}