package com.peter.room_example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logs")
data class Log(val msg : String, val timestamp: String){

    @PrimaryKey(autoGenerate = true)
    var id : Long = 0
}