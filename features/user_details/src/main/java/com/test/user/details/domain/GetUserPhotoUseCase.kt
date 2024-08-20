package com.test.user.details.domain

import com.test.data.api.AppDataPreference

class GetUserPhotoUseCase(private val dataStore: AppDataPreference) {

    suspend fun execute(): String {
        return dataStore.getUserPhoto()
    }

    suspend fun execute(uri: String) {
        return dataStore.setUserPhoto(uri)
    }
}