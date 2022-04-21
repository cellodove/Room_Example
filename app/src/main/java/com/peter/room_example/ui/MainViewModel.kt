package com.peter.room_example.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peter.room_example.data.Log
import com.peter.room_example.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    enum class FragmentStep {
        EDIT, LOG
    }
    val appRepository = AppRepository.getInstance(Application())

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