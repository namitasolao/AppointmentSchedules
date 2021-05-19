package com.namita.mynotepad.apptpage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.namita.mynotepad.database.Appointments
import com.namita.mynotepad.database.AppointmentsDAO
import kotlinx.coroutines.launch
import java.util.*

class ApptViewModel(
    val database : AppointmentsDAO ,
    application: Application
) : AndroidViewModel(application) {

    fun onAdd(
        date: String,
        time: String,
        description: String
    ){
         viewModelScope.launch {
             val newappt = Appointments(
                 apptDate = date,
                 apptTime = time,
                 details = description
             )

             database.insert(newappt)
         }
    }



}