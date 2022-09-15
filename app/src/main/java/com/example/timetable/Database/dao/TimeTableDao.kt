package com.example.timetable.Database.dao

import androidx.room.*
import com.example.timetable.Models.TimeTable

@Dao
interface TimeTableDao {

    @Insert
    fun addTimeTable(timeTable: TimeTable)

    @Query("select * from timetable")
    fun getAllTimeTable():List<TimeTable>

    @Update
    fun updateTimeTable(timeTable: TimeTable)

    @Delete
    fun deleteTimeTable(timeTable: TimeTable)
}