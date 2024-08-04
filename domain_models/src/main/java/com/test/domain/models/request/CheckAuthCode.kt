package com.test.domain.models.request

data class CheckAuthCode(
    val phone: String,
    val code: String
)