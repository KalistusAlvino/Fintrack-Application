package com.example.fintrack.data.manger

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.fintrack.domain.manger.LocalUserManger
import com.example.fintrack.util.Constants
import com.example.fintrack.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserMangeImpl(
    private val context: Context
): LocalUserManger {
    override fun readLoginStatus(): Flow<Boolean> {
        return context.dataStore.data.map { preferences->
            preferences[PreferenceKeys.IS_LOGGED_IN] ?: false
        }
    }

    override suspend fun saveLoginStatus() {
        context.dataStore.edit { settings ->
            settings[PreferenceKeys.IS_LOGGED_IN] = true
        }
    }

    override fun readOnBoardingStatus(): Flow<Boolean> {
        return context.dataStore.data.map { preferences->
            preferences[PreferenceKeys.IS_ONBOARDING_DONE] ?: false
        }
    }

    override suspend fun saveOnBoardingStatus() {
        context.dataStore.edit { settings ->
            settings[PreferenceKeys.IS_ONBOARDING_DONE] = true
        }
    }

}
private val readOnlyProperty = preferencesDataStore(name = USER_SETTINGS)

val Context.dataStore: DataStore<Preferences> by readOnlyProperty

private object PreferenceKeys {
    val IS_LOGGED_IN = booleanPreferencesKey(Constants.IS_LOGGED_IN)
    val IS_ONBOARDING_DONE = booleanPreferencesKey(Constants.IS_ONBOARDING_DONE)
}