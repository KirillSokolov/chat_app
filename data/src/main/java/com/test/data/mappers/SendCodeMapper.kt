package com.test.data.mappers

import com.test.data.network.model.SendCodeNetwork
import com.test.domain.models.response.SendAuthCodeResponse

fun SendCodeNetwork.toDomain(): SendAuthCodeResponse.Authorization {
    return SendAuthCodeResponse.Authorization(isSuccess = is_success)
}