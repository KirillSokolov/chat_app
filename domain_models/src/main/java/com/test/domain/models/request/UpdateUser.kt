package com.test.domain.models.request

data class UpdateUser(
    val name: String,
    val username: String,
    val birthday: String = "2024-08-20",
    val city:String = "string",
    val vk:String = "string",
    val instagram:String = "string",
    val status:String = "string",
    val avatar: Avatar = Avatar()
)

data class Avatar(
    val filename: String  = "",
    val base_64: String = ""

)


