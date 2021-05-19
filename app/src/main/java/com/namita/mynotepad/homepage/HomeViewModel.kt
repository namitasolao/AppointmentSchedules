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

    private val _appointments = MutableLiveData<Appointments?>()

    val appointments : LiveData<Appointments?>
        get() = _appointments

    init{
        initializeAppointments()
    }

    private fun initializeAppointments(){
        viewModelScope.launch {
            _appointments.value = getAppointmentsFromDatabase()
        }
    }

    private suspend fun getAppointmentsFromDatabase(): Appointments? {
        return database.getAppt()
    }

//     appointments.value = getAppointmentsFromDatabase()


}