package com.test.data.mappers

import com.data.UserEntity
import com.test.domain.models.user.User

fun User.toBase() : UserEntity{
    return UserEntity(
        id = id,
        name = name,
        nickname = nickName,
        zodiac = zodiac,
        birth = birth,
        city = city,
        about_me = aboutMe,
        phone = phone)
}