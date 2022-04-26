package com.peter.room_example.repository

import android.app.Application
import com.peter.room_example.data.AppDatabase
import com.peter.room_example.data.Log
import com.peter.room_example.data.LogDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository (applicationContext : Application){
    private val logDao : LogDao
    private var context : Application
    init {
        val appDatabase = AppDatabase.getInstance(applicationContext)
        this.context = applicationContext
        logDao = appDatabase.logDao()
    }
    companion object{
        private lateinit var instance: AppRepository

        fun getInstance(application: Application): AppRepository{
            if (!::instance.isInitialized){
                instance = AppRepository(application)
            }
            return instance
        }
    }

    fun getAllMemoList(): List<Log> = logDao.getAll()

    suspend fun insertLog(log : Log){
        withContext(Dispatchers.IO){
            logDao.insertAll(log)
        }
    }
    suspend fun deleteLog(log: Log){
        withContext(Dispatchers.IO){
            logDao.deleteItem(log)
        }
    }
    fun clearAllTables(){
        AppDatabase.getInstance(context).clearAllTables()
    }
}