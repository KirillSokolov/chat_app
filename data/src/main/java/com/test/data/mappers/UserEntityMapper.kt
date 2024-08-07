package com.test.data.mappers

import com.data.UserEntity
import com.test.domain.models.user.User

fun UserEntity.toDomain() : User {
    return User(
        name = name,
        nickName = nickname,
        phone = phone,
        city = city,
        birth = birth,
        aboutMe = about_me,
        zodiac = zodiac
    )
}