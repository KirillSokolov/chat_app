package com.test.data.repository

import com.test.data.mappers.toDomain
import com.test.data.network.api.UsersApi
import com.test.data.api.AuthorizationRepository
import com.test.domain.models.request.CheckAuthCode
import com.test.domain.models.request.SendAuthCode
import com.test.domain.models.response.Authorization
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(val api: UsersApi, private val dispatcher: CoroutineDispatcher,
) : AuthorizationRepository {

    override suspend fun checkAuthCode(checkAuthCode: CheckAuthCode): Authorization  = withContext(dispatcher){
        return@withContext api.checkAuthCode(checkAuthCode).body()!!.toDomain()
    }

    override suspend fun sendAuthCode(sendAuthCode: SendAuthCode): Boolean = withContext(dispatcher){
        return@withContext api.sendAuthCode(sendAuthCode).body()!!
    }
}