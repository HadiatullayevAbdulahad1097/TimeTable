package com.example.timetable.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TimeTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var date: String? = null,
    var fromTime: String? = null,
    var untilTime: String? = null,
    var type: String? = null
)
