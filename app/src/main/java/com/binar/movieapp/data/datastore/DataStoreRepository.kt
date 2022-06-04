package com.binar.movieapp.data.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
//import com.kharismarizqii.movieapp.data.datastore.DataStoreRepository.PreferencesKeys.email
//import com.kharismarizqii.movieapp.data.datastore.DataStoreRepository.PreferencesKeys.pass
//import com.kharismarizqii.movieapp.data.datastore.DataStoreRepository.PreferencesKeys.username
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

const val PREFERENCE_NAME = "my_preference"

class DataStoreRepository(context: Context)  {

    private object PreferencesKeys{
        val username = preferencesKey<String>("my_user")
//        val pass = preferencesKey<String>("my_pass")
//        val email = preferencesKey<String>("my_email")
//        val passconf = preferencesKey<String>("my_conf")
    }

    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = PREFERENCE_NAME
    )

    suspend fun saveData(username: String) {
        dataStore.edit { preference ->
            preference[PreferencesKeys.username] = username
//            preference[PreferencesKeys.email] = email
//            preference[PreferencesKeys.pass] = pass
//            preference[PreferencesKeys.passconf] = passConf
        }
    }

    val readData: Flow<String> = dataStore.data
        .catch { exception ->
            if(exception is IOException){
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            val username = preference[PreferencesKeys.username] ?: ""
            username
        }

}
