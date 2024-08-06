package com.test.data.network.model

data class AuthorizationNetwork(
    val refresh_token: String?,
    val access_token : String?,
    val user_id: Long,
    val is_user_exists: Boolean
)
