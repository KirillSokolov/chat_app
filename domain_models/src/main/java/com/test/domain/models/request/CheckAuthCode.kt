package com.test.domain.models.request

import com.test.domain.models.exception.IllegalArgumentCodeException
import com.test.domain.models.exception.IllegalArgumentPhoneException

data class CheckAuthCode(
    val phone: String,
    val code: String
)

class CheckAuthCodeBuilder {
    var phone: String = ""
    var code: String = ""

    fun setPhone(value: String) = apply { phone = value }
    fun setCode(value: String) = apply { code = value }

    fun build(): CheckAuthCode{
        if (phone.isEmpty()) throw IllegalArgumentPhoneException()
        if (code.isEmpty()) throw IllegalArgumentCodeException()
        return CheckAuthCode(phone, code)
    }

}

fun checkAuthCodeBuilder(init: CheckAuthCodeBuilder.()-> Unit) = CheckAuthCodeBuilder().apply(init).build()