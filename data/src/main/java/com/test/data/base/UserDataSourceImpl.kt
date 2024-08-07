package com.test.data.base

import com.test.data.api.database.UserDataSource
import com.test.data.mappers.toBase
import com.test.data.mappers.toDomain
import com.test.domain.models.user.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(private val dispatcher: CoroutineDispatcher): UserDataSource {
    override suspend fun getUser(): User = withContext(dispatcher){
        return@withContext dataBaseChat.userDbQueries.getUser().executeAsOne().toDomain()
    }

    override suspend fun updateUser(user: User) = withContext(dispatcher) {
        dataBaseChat.userDbQueries.insertUser(user.toBase())
    }
}