package com.cherubini.news.data.manager

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.cherubini.news.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATA_STORE =  "dataStore"
private const val USER_LOGGED_IN_DATA_STORE =  "userLoggedIn"

class LocalUserManagerImplementation(private val context: Context): LocalUserManager {
    override suspend fun storeUserLoggedIn(userName: String?) {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.LOGGED_IN] = userName.orEmpty()
        }
    }

    override fun getUserLoggedIn(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.LOGGED_IN]
        }
    }
}

private val Context.dataStore by preferencesDataStore(DATA_STORE)

private object PreferencesKeys{
    val LOGGED_IN = stringPreferencesKey(name = USER_LOGGED_IN_DATA_STORE)
}