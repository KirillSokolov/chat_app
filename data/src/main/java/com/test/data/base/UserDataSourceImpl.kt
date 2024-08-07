package com.test.data.base

import com.test.data.api.database.UserDataSource
import com.test.data.mappers.toBase
import com.test.domain.models.user.User
import javax.inject.Inject

//private val databasechat: databasechat
class UserDataSourceImpl @Inject constructor(): UserDataSource {
    override suspend fun getUser(): User {
        //sqlDriver.
        return User()
    }

    override suspend fun updateUser(user: User) {
        dataBaseChat.userDbQueries.insertUser(user.toBase())
    }
}