package com.peter.room_example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.peter.room_example.data.Log
import com.peter.room_example.repository.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    enum class FragmentStep {
        EDIT, LOG
    }
    private val appRepository = AppRepository.getInstance(getApplication())

    val liveFragmentStep = MutableLiveData(FragmentStep.EDIT)
    val getLogData = MutableLiveData<List<Log>>()

    fun saveLog(log : String){
        viewModelScope.launch {
            appRepository.insertLog(Log(log,getCurrentDate()))
        }
    }

    fun deleteLog(position : Int) = ioScope.launch{
        val log = getLogData.value?.get(position)
        if (log != null){
            appRepository.deleteLog(log)
            getLog()
        }
    }

    fun clearAllTables() = ioScope.launch{
        appRepository.clearAllTables()
        getLog()
    }

    fun getLog() = ioScope.launch{
        getLogData.postValue(appRepository.getAllMemoList())
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        return dateFormat.format(Date())
    }

}