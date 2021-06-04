package com.namita.mynotepad.homepage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.namita.mynotepad.database.Appointments
import com.namita.mynotepad.database.AppointmentsDAO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(val database : AppointmentsDAO,
                    application: Application) :AndroidViewModel(application) {

    //val allAppointments = database.getAllAppt()

    private val _appointments = MutableLiveData<List<Appointments>>()

    val appointments : LiveData<List<Appointments>>
        get() = _appointments

    val checkedAppointmentId = MutableLiveData<Int>()

    private val checkedAppointments = mutableSetOf<Int>()

    init{
        checkedAppointmentId.observeForever {
            if (checkedAppointments.contains(it)) {
                checkedAppointments.remove(it)
            } else {
                checkedAppointments.add(it)
            }
        }
    }

    fun initializeAppointments(){
        viewModelScope.launch {
            _appointments.value = getAppointmentsFromDatabase()
        }
    }

    private suspend fun getAppointmentsFromDatabase(): List<Appointments> {
        return database.getAllAppt()
    }

//     appointments.value = getAppointmentsFromDatabase()

    fun onDelete() {
        var checkList: List<Int>

        viewModelScope.launch {
            checkedAppointments.forEach {
                database.deleteAppointment(it)
            }
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