package com.test.data.mappers

import com.test.data.network.model.AuthorizationNetwork
import com.test.domain.models.response.CheckAuthCodeResponse

fun AuthorizationNetwork.toDomain(): CheckAuthCodeResponse.Verification {
    return CheckAuthCodeResponse.Verification(
        refreshToken = refresh_token?:"",
        accessToken =  access_token?:"",
        userId = user_id,
        isUserExists = is_user_exists,
    )
}