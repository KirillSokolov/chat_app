package com.test.authorization.domain

import com.test.data.api.AppDataPreference
import javax.inject.Inject

internal class SaveRefreshTokenUseCase @Inject constructor(private val preference: AppDataPreference) {

    suspend fun execute(refreshToken: String) {
        preference.setRefreshToken(refreshToken)
    }
}