package com.test.data.network.model

data class AvatarNetwork(
   val avatars: Avatars
)

data class Avatars(
    val avatar: String,
    val bigAvatar: String,
    val miniAvatar: String
)