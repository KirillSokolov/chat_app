package com.test.domain.models.response

data class Registration(
    val refreshToken: String,
    val accessToken: String,
    val userId:Long
)
