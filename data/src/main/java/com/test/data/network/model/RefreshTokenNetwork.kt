package com.test.data.network.model

data class RefreshTokenNetwork (
    val refresh_token: String,
    val access_token : String,
    val user_id: Long
)