package com.namita.mynotepad.apptpage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.namita.mynotepad.database.Appointments
import com.namita.mynotepad.database.AppointmentsDAO
import kotlinx.coroutines.launch
import java.sql.Time
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.time.LocalDate as LocalDate

class ApptViewModel(
    val database : AppointmentsDAO ,
    application: Application
) : AndroidViewModel(application) {

    fun onAdd(
        date: Long?,
        time: Long?,
        description: String
    ) : String? {
        var isValid = validateData(date,time, description)
        var outputStr : String? = null

        if(isValid) {
            isValid = validateDate(date!!)
            if (isValid) {

                viewModelScope.launch {
                    val newappt = Appointments(
                        apptDate = Date(date!!),
                        apptTime = Time(time!!),
                        details = description
                    )

                    database.insert(newappt)
                }
            }
            else {
                outputStr = "Enter the valid date!"
            }
        } else {
            outputStr = "Enter all values!"
        }


        return outputStr
    }

    private fun validateData(
        date : Long?,
        time: Long?,
        description: String
    ) : Boolean {

        if(date == null || time == null || description.isEmpty())
            return false

        return true
    }

    private fun validateDate( date : Long) : Boolean{
        return ((date) >= System.currentTimeMillis())
    }



}