package com.test.domain.models.request

import com.test.domain.models.exception.IllegalArgumentNameException
import com.test.domain.models.exception.IllegalArgumentPhoneException
import com.test.domain.models.exception.IllegalArgumentUsernameException

data class Registration(
    val phone: String,
    val name: String,
    val username: String,
)
class RegistrationBuilder {
    var phone: String = ""
    var name: String = ""
    var username: String = ""

    fun setPhone(value: String) = apply { phone = value }
    fun setName(value: String) = apply { name = value }
    fun setUsername(value: String) = apply { username = value }

    fun build(): Registration{
        if (phone.isEmpty()) throw IllegalArgumentPhoneException()
        if (name.isEmpty()) throw IllegalArgumentNameException()
        if (username.isEmpty()) throw IllegalArgumentUsernameException()
        return Registration(phone, name, username)
    }

}

fun registrationBuilder(init: RegistrationBuilder.()-> Unit) = RegistrationBuilder().apply(init).build()