package com.namita.mynotepad.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AppointmentsDAO {
    @Insert
    suspend fun insert(inputappt : Appointments)

    @Update
    suspend fun update(inputappt : Appointments)

    @Query("Delete from appointments_tab" )
    suspend fun delete()

//    @Query("Select * from appointments_tab where apptid = (:apptId)")
//    suspend fun getAppt(apptId: Int) : Appointments

    @Query("Select * from appointments_tab order by apptid desc LIMIT 1")
    suspend fun getAppt() : Appointments?

    @Query("Select * from appointments_tab order by apptid desc")
    suspend fun getAllAppt() : List<Appointments>

    @Query("Select count(1) from appointments_tab ")
    suspend fun getCount() : Int

}