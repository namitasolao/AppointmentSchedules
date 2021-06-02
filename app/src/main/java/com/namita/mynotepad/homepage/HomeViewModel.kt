package com.namita.mynotepad.homepage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.namita.mynotepad.database.Appointments
import com.namita.mynotepad.database.AppointmentsDAO
import kotlinx.coroutines.launch

class HomeViewModel(val database : AppointmentsDAO,
                    application: Application) :AndroidViewModel(application) {

    //val allAppointments = database.getAllAppt()

    private val _appointments = MutableLiveData<List<Appointments>>()

    val appointments : LiveData<List<Appointments>>
        get() = _appointments

//    init{
//        initializeAppointments()
//    }

    fun initializeAppointments(){
        viewModelScope.launch {
            _appointments.value = getAppointmentsFromDatabase()
        }
    }

    private suspend fun getAppointmentsFromDatabase(): List<Appointments> {
        return database.getAllAppt()
    }

//     appointments.value = getAppointmentsFromDatabase()

    fun onDelete(){
        viewModelScope.launch {
            database.delete()
        }
    }

    fun rowsExist() : Int{
        var count = 0
        viewModelScope.launch {
             count = database.getCount()
        }
        return count
    }
}