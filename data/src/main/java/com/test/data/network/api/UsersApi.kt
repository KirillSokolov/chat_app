package com.test.data.network.api

import com.test.data.network.model.AuthorizationNetwork
import com.test.data.network.model.AvatarNetwork
import com.test.data.network.model.RefreshTokenNetwork
import com.test.data.network.model.RegistrationNetwork
import com.test.data.network.model.SendCodeNetwork
import com.test.domain.models.request.CheckAuthCode
import com.test.domain.models.request.RefreshToken
import com.test.domain.models.request.Registration
import com.test.domain.models.request.SendAuthCode
import com.test.domain.models.request.UpdateUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT


interface UsersApi {

    @POST("users/send-auth-code/")
    suspend fun sendAuthCode(@Body request: SendAuthCode): Response<SendCodeNetwork>

    @POST("users/check-auth-code/")
    suspend fun checkAuthCode(@Body request: CheckAuthCode) : Response<AuthorizationNetwork>

    @POST("users/register/")
    suspend fun register(@Body request: Registration) : Response<RegistrationNetwork>

    @POST("users/refresh-token/")
    suspend fun refreshToken(@Body request: RefreshToken) : Response<RefreshTokenNetwork>

    @PUT("users/me/")
    suspend fun updateUser(@Header("Authorization") accessToken:String, @Body request: UpdateUser) : Response<AvatarNetwork>
}