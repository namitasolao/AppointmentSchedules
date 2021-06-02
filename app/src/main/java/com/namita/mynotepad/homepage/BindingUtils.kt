package com.namita.mynotepad.homepage

import android.icu.text.SimpleDateFormat
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.namita.mynotepad.database.Appointments
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class BindingUtils {

    companion object {

        @JvmStatic
        @BindingAdapter("apptTime")
        fun TextView.formatApptTime(item: Appointments) {
//        text = SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm")
//            .format(item.apptTime).toString()

            text = item.apptTime

        }

        @JvmStatic
        @BindingAdapter("apptDate")
        fun TextView.formatApptDate(item: Appointments) {
            //text = SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm")
            //  .format(item.apptDate).toString()

            //text = LocalDate.parse(item.apptDate, DateTimeFormatter.ISO_DATE).toString()
            text = item.apptDate

        }

        @JvmStatic
        @BindingAdapter("apptDetails")
        fun TextView.formatApptDetails(item: Appointments) {
            text = item.details

        }
    }

}
