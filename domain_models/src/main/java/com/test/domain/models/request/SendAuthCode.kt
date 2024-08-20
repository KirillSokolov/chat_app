package com.test.domain.models.request

import com.test.domain.models.exception.IllegalArgumentPhoneException

data class SendAuthCode(
    val phone: String
)

class SendAuthCodeBuilder {
    var phone: String = ""
    var code: String = ""

    fun setPhone(value: String) = apply { phone = value }

    fun build(): SendAuthCode{
        if (phone.isEmpty()) throw IllegalArgumentPhoneException()
        return SendAuthCode("$code $phone")
    }

}

fun sendAuthCodeBuilder(init: SendAuthCodeBuilder.()-> Unit) = SendAuthCodeBuilder().apply(init).build()
