package com.test.data.network.api

import com.test.data.network.model.AuthorizationNetwork
import com.test.data.network.model.RefreshTokenNetwork
import com.test.data.network.model.RegistrationNetwork
import com.test.domain.models.request.CheckAuthCode
import com.test.domain.models.request.SendAuthCode
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface UsersApi {

    @POST("users/send-auth-code/")
    fun sendAuthCode(@Body request: SendAuthCode): Response<Boolean>

    @POST("users/check-auth-code/")
    fun checkAuthCode(@Body request: CheckAuthCode) : Response<AuthorizationNetwork>

    @POST("users/register/")
    fun register(@Body request: SendAuthCode) : Response<RegistrationNetwork>

    @POST("users/refresh-token/")
    fun refreshToken(@Body request: SendAuthCode) : Response<RefreshTokenNetwork>
}