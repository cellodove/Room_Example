package com.peter.room_example.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Log::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object{
        private lateinit var instance: AppDatabase

        fun getInstance(application: Application): AppDatabase{
            if (!::instance.isInitialized){
                instance =
                    Room.databaseBuilder(
                        application,
                        AppDatabase::class.java,
                        "memo-db"
                    ).build()
            }
            return instance
        }
    }
    abstract fun logDao() : LogDao
}