package com.cherubini.news.data.manager

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.cherubini.news.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val USER_SETTINGS_DATA_STORE =  "userSettings"
private const val APP_ENTRY_DATA_STORE =  "appEntry"

class LocalUserManagerImplementation(private val context: Context): LocalUserManager {
    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] ?: false
        }
    }
}

private val Context.dataStore by preferencesDataStore(USER_SETTINGS_DATA_STORE)

private object PreferencesKeys{
    val APP_ENTRY = booleanPreferencesKey(name = APP_ENTRY_DATA_STORE)
}