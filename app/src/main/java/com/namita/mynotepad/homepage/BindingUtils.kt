package com.namita.mynotepad.homepage

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.text.format.DateFormat
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.namita.mynotepad.apptpage.DATE_FORMAT
import com.namita.mynotepad.database.Appointments
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class BindingUtils {

    companion object {

        @SuppressLint("SimpleDateFormat")
        @JvmStatic
        @BindingAdapter("apptTime")
        fun TextView.formatApptTime(item: Appointments) {
            text = SimpleDateFormat("HH:mm").format(item.apptTime).toString()
        }

        @SuppressLint("SimpleDateFormat")
        @JvmStatic
        @BindingAdapter("apptDate")
        fun TextView.formatApptDate(item: Appointments) {
            text = SimpleDateFormat("EEEE MMM-dd-yyyy")
              .format(item.apptDate)

            //text = LocalDate.parse(item.apptDate, DateTimeFormatter.ISO_DATE).toString()
//            text = DateFormat.format(DATE_FORMAT, item.apptDate)

        }

        @JvmStatic
        @BindingAdapter("apptDetails")
        fun TextView.formatApptDetails(item: Appointments) {
            text = item.details

        }
    }

}
