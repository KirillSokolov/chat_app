package com.test.domain.models.response

sealed class RegistrationResponse {
    data class Registration(
        val refreshToken: String,
        val accessToken: String,
        val userId: Long
    ):RegistrationResponse()

    class Error(val message: String, val code: Int,): RegistrationResponse()

}
