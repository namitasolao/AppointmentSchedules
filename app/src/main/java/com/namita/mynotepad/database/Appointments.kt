package com.namita.mynotepad.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.*

@Entity(tableName = "appointments_tab")
data class Appointments(

    @PrimaryKey(autoGenerate = true)
    val apptid: Int = 0,

    @ColumnInfo(name = "date")
    val apptDate: String,

    @ColumnInfo(name = "time")
    val apptTime: String,

    @ColumnInfo(name = "description")
    val details: String
)
