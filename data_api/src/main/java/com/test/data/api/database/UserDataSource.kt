package com.test.data.api.database

import com.test.domain.models.user.User

interface UserDataSource {
    suspend fun getUser(): User
    suspend fun updateUser(user: User)
}