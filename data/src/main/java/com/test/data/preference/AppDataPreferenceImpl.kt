package com.test.data.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.test.data.api.AppDataPreference
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

val REFRESH_TOKEN = stringPreferencesKey("refresh_token")

class AppDataPreferenceImpl @Inject constructor(private val dataStore : DataStore<Preferences>, val dispatcher: CoroutineDispatcher): AppDataPreference {

    override suspend fun getRefreshToken():String = withContext(dispatcher){
        return@withContext dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN] ?: ""
        }.first()
    }

    override suspend fun setRefreshToken(refreshToken: String ): Unit = withContext(dispatcher) {
         dataStore.edit { preferences ->
            preferences[REFRESH_TOKEN] = refreshToken
        }
    }
}