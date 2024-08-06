package com.test.data.api

import com.test.domain.models.user.User

interface UserRepository {
    suspend fun getUser() : User
    suspend fun updateUser()
}