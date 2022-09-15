package com.example.timetable.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.timetable.Database.dao.TimeTableDao
import com.example.timetable.Models.TimeTable

@Database(entities = [TimeTable::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun biletDuo(): TimeTableDao
    companion object{
        var appDataBase: AppDataBase? = null

        @Synchronized
        fun getInstance(context: Context): AppDataBase {
            if (appDataBase ==null){
                appDataBase = Room.databaseBuilder(context, AppDataBase::class.java,"bilet_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return appDataBase!!
        }
    }
}