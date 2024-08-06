package com.test.data.mappers

import com.test.data.network.model.RegistrationNetwork
import com.test.domain.models.response.RegistrationResponse

fun RegistrationNetwork.toDomain(): RegistrationResponse.Registration {
    return RegistrationResponse.Registration(
        refreshToken = refresh_token,
        accessToken = access_token,
        userId = user_id
    )
}