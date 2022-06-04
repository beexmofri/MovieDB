package com.binar.movieapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.binar.movieapp.data.datastore.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (application: Application): AndroidViewModel(application) {

    private val repository = DataStoreRepository(application)

    val readDataStore = repository.readData.asLiveData()

    fun saveDatastore(username:String) = viewModelScope.launch(Dispatchers.IO){
        repository.saveData(username)
    }
//
//    fun getData(username:String, email:String, pass:String, passConf:String) = viewModelScope.launch(Dispatchers.IO) {
//        repository.saveData()
//    }
//
//    fun getDataEmail() = viewModelScope.launch(Dispatchers.IO) {
//        repository.readEmail.collect {
//
//        }
//    }
//
//    fun getDataPass() = viewModelScope.launch(Dispatchers.IO) {
//        repository.readPass.collect {
//
//
//        }
//    }
}