package com.test.data.mappers

import com.test.data.network.model.RegistrationNetwork
import com.test.domain.models.response.Registration

fun RegistrationNetwork.toDomain(): Registration {
    return Registration(
        refreshToken = refresh_token,
        accessToken = access_token,
        userId = user_id
    )
}