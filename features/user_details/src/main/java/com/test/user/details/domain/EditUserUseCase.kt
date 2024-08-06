package com.test.user.details.domain

import com.test.data.api.UserRepository
import com.test.domain.models.user.User
import javax.inject.Inject

internal class EditUserUseCase @Inject constructor(private val repository: UserRepository) {

    suspend fun execute(user: User) {
        repository.updateUser()
    }
}