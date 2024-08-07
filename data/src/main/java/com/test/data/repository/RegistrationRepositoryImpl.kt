package com.test.data.repository

import com.test.data.mappers.toDomain
import com.test.data.network.api.UsersApi
import com.test.data.api.repository.RegistrationRepository
import com.test.domain.models.request.Registration
import com.test.domain.models.response.RegistrationResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(val api: UsersApi, private val dispatcher: CoroutineDispatcher) :
    RegistrationRepository {

    override suspend fun registration(registration: Registration): RegistrationResponse = withContext(dispatcher){
        val response = api.register(registration)
        return@withContext if(response.isSuccessful) {
            response.body()!!.toDomain()
        }else
            RegistrationResponse.Error(response.message(), response.code())
    }
}