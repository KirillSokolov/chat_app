package com.test.data.preference

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

//lateinit var dataStore: DataStore<Preferences>
val Application.dataStore: DataStore<Preferences> by preferencesDataStore(name = "")

fun createDataStore(application: Application){
 //   dataStore = application.dataStore
}