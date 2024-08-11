package com.test.authorization.presentation.navigation.sendcode

const val DEFAULT_CODE = 1

internal sealed class NextScreen {
    data object Nothing : NextScreen()
    class CheckAuthCodeFragment : NextScreen()
    class Error(val msg: String, val code: Int = DEFAULT_CODE) : NextScreen()
}