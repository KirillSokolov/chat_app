package com.test.data.api

import kotlinx.coroutines.flow.Flow

interface AppDataPreference {

    suspend fun getRefreshToken(): String
    suspend fun setRefreshToken(token:String)

    suspend fun getUserPhoto(): String
    suspend fun setUserPhoto(uri:String)
}