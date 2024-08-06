package com.test.data.repository

import com.test.data.mappers.toDomain
import com.test.data.network.api.UsersApi
import com.test.data.api.RegistrationRepository
import com.test.domain.models.request.SendAuthCode
import com.test.domain.models.response.Registration
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(val api: UsersApi, private val dispatcher: CoroutineDispatcher) :
    RegistrationRepository {
    override suspend fun registration(sendAuthCode: SendAuthCode): Registration = withContext(dispatcher){
        return@withContext api.register(sendAuthCode).body()!!.toDomain()

    }
}