package com.test.domain.models.response

sealed class CheckAuthCodeResponse{
    data class Verification(
        val refreshToken: String,
        val accessToken: String,
        val userId:Long,
        val isUserExists: Boolean
    ):CheckAuthCodeResponse()

    class Error(val message: String, val code: Int,): CheckAuthCodeResponse()
}
