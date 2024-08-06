package com.test.domain.models.response

sealed class SendAuthCodeResponse{
    data class Authorization(val isSuccess: Boolean): SendAuthCodeResponse()
    class Error(val message: String, val code: Int,): SendAuthCodeResponse()
}


