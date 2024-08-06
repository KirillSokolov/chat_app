package com.test.data.repository

import com.test.data.api.UserRepository
import com.test.domain.models.user.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl@Inject constructor(private val dispatcher: CoroutineDispatcher) :
    UserRepository {
    override suspend fun getUser(): User = withContext(dispatcher) {
        return@withContext User()
    }

    override suspend fun updateUser()  = withContext(dispatcher){
        TODO("Not yet implemented")
    }
}