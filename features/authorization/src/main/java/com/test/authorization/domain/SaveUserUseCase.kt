package com.test.authorization.domain

import com.test.data.api.repository.UserRepository
import com.test.domain.models.user.User
import javax.inject.Inject

internal class SaveUserUseCase @Inject constructor(private val repository: UserRepository) {

    suspend fun execute(user: User) {
         repository.updateLocalUser(user)
    }
}