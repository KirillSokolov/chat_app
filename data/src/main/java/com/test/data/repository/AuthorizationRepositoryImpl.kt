package com.test.data.repository

import com.test.data.mappers.toDomain
import com.test.data.network.api.UsersApi
import com.test.data.api.AuthorizationRepository
import com.test.domain.models.request.CheckAuthCode
import com.test.domain.models.request.SendAuthCode
import com.test.domain.models.response.CheckAuthCodeResponse
import com.test.domain.models.response.SendAuthCodeResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(val api: UsersApi, private val dispatcher: CoroutineDispatcher,
) : AuthorizationRepository {

    override suspend fun checkAuthCode(checkAuthCode: CheckAuthCode): CheckAuthCodeResponse  = withContext(dispatcher){
        val response = api.checkAuthCode(checkAuthCode)
        return@withContext if(response.isSuccessful) {
            response.body()!!.toDomain()
        }else{
            CheckAuthCodeResponse.Error(response.message(), response.code())
        }
    }

    override suspend fun sendAuthCode(sendAuthCode: SendAuthCode): SendAuthCodeResponse = withContext(dispatcher){
        val response = api.sendAuthCode(sendAuthCode)
        return@withContext if(response.isSuccessful) {
            response.body()!!.toDomain()
        }else{
            SendAuthCodeResponse.Error(response.message(), response.code())
        }
    }
}