package com.test.data.repository

import com.test.data.api.AppDataPreference
import com.test.data.api.repository.UserRepository
import com.test.data.api.database.UserDataSource
import com.test.data.network.api.UsersApi
import com.test.data.temp.UserData
import com.test.domain.models.request.Avatar
import com.test.domain.models.request.RefreshToken
import com.test.domain.models.request.UpdateUser
import com.test.domain.models.user.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl@Inject constructor(private val api: UsersApi,
                                            private val userDataSource: UserDataSource,
                                            private val dataStore: AppDataPreference,
                                            private val dispatcher: CoroutineDispatcher) :
    UserRepository {
    override suspend fun getUser(): User = withContext(dispatcher) {
        return@withContext userDataSource.getUser()
    }

    override suspend fun updateLocalUser(user: User) = withContext(dispatcher){
        userDataSource.updateUser(user)
    }

    override suspend fun updateGlobalUser(user: User): Unit = withContext(dispatcher){
        userDataSource.updateUser(user)
        val photo = dataStore.getUserPhoto()

//       val result = api.updateUser( UserData.accessToken, UpdateUser(
//            name =user.name,
//            username =  user.nickName,
//            avatar = if(photo.isEmpty())
//                         Avatar()
//                    else
//                         Avatar(filename = System.currentTimeMillis().toString(), photo)
//
//        ))
//        result.isSuccessful
    }

    override suspend fun getAccessToken(refreshToken: RefreshToken)= withContext(dispatcher) {
        val response = api.refreshToken(refreshToken)

        if (response.isSuccessful){
            response.body()?.let {
                dataStore.setRefreshToken(it.refresh_token)
                UserData.accessToken = it.access_token
            }
        }
    }
}