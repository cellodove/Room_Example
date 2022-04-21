package com.peter.room_example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peter.room_example.data.Log
import com.peter.room_example.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    enum class FragmentStep {
        EDIT, LOG
    }
    private val appRepository = AppRepository.getInstance(getApplication())

    val liveFragmentStep = MutableLiveData(FragmentStep.EDIT)

    fun saveLog(log : String){
        viewModelScope.launch {
            appRepository.insertLog(Log(log,System.currentTimeMillis()))
        }
    }

    fun deleteLog(){
        viewModelScope.launch {
            appRepository.deleteLog()
        }
    }

    fun getLog(){
        viewModelScope.launch {
            appRepository.getAllMemoList()
        }
    }
}