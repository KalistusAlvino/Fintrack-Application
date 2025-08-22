package com.example.fintrack.data.pref

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.fintrack.di.model.user.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
class UserPreference @Inject constructor(
    @ApplicationContext context: Context
){
    private val dataStore = context.dataStore

    suspend fun saveSession(user: User) {
        dataStore.edit { preferences ->
            preferences[USERID_KEY] = user.id
            preferences[USERNAME_KEY] = user.username
            preferences[TOKEN_KEY] = user.token
            preferences[IS_LOGIN_KEY] = true
            Log.d("UserPreference", "Saved session: ${user.id}, ${user.username}")
        }
    }

    fun getSession(): Flow<User> {
        return dataStore.data.map { preferences ->
            User(
                id = preferences[USERID_KEY] ?: 0,
                username = preferences[USERNAME_KEY] ?: "",
                token = preferences[TOKEN_KEY] ?: "",
                isLogin = preferences[IS_LOGIN_KEY] ?:false
            )
        }
    }
    fun readLoginStatus(): Flow<Boolean> {
        return getSession().map { it.isLogin }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            Log.d("UserPreference", "Token = ${TOKEN_KEY}")
            preferences.clear()
        }
    }
    companion object {
        private val USERID_KEY = intPreferencesKey("id")
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val IS_LOGIN_KEY = booleanPreferencesKey("is_login")
    }
}