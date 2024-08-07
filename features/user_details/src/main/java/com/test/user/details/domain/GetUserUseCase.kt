package com.test.user.details.domain

import com.test.data.api.repository.UserRepository
import com.test.domain.models.user.User
import javax.inject.Inject

internal class GetUserUseCase @Inject constructor(private val repository: UserRepository) {

    suspend fun execute(): User {
        return repository.getUser()
    }
}