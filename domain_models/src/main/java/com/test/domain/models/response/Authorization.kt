package com.test.domain.models.response

data class Authorization(
    val refreshToken: String,
    val accessToken: String,
    val userId:Long,
    val isUserExists: Boolean
)
