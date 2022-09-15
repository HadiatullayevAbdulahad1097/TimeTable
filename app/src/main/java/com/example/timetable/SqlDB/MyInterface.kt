package com.example.timetable.SqlDB

import com.example.timetable.Models.TimeWork

interface MyInterface {
    fun addTimeWork(timeWork: TimeWork)

    fun readTimeWork() : ArrayList<TimeWork>

    fun updateTimeWork(timeWork: TimeWork)

    fun deleteTimeWork(timeWork: TimeWork)
}