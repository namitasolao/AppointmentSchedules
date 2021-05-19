package com.namita.mynotepad.apptpage

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.namita.mynotepad.database.AppointmentsDAO
import com.namita.mynotepad.homepage.HomeViewModel

class ApptViewModelFactory(
    val datasrc:AppointmentsDAO ,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(datasrc, application) as T
        }
        if (modelClass.isAssignableFrom(ApptViewModel::class.java)) {
            return ApptViewModel(datasrc, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}