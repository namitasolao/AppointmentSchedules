package com.namita.mynotepad.apptpage

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.namita.mynotepad.R
import com.namita.mynotepad.database.ApptDatabase
import com.namita.mynotepad.databinding.FragmentApptPageBinding
import java.sql.Time
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

const val TIME_REQUEST_KEY = "TIME_REQUEST_KEY"
const val DATE_REQUEST_KEY = "DATE_REQUEST_KEY"
const val DATE_FORMAT = "yyyy-MMM-dd"
const val TIME_FORMAT = "hh:mm"

class ApptPageFragment : Fragment(R.layout.fragment_appt_page) {

    private lateinit var binding: FragmentApptPageBinding

    private var selectedDate: Long? = null
    private var selectedTime: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //FragmentApptPageBinding is automatically created
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_appt_page,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val datasource = ApptDatabase.getInstance(application).appointmentsDAO

        val modelFactory = ApptViewModelFactory(datasource,application)

        binding.apptPage = ViewModelProvider(this,modelFactory).get(ApptViewModel::class.java)

        binding.lifecycleOwner = this

        with(binding) {
            timeText.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_apptPageFragment_to_timePickerFragment)
            }
            dateText.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_apptPageFragment_to_datePickerFragment)
            }
            okButton.setOnClickListener {
                val isSuccessful = apptPage?.onAdd(
                    selectedDate,
                    //Date(dateText.text),
                    selectedTime,
                    detailsText.text.toString()
                ) ?: null

                if(isSuccessful.isNullOrEmpty()) {
                    //Navigation.findNavController(it).navigate(R.id.action_apptPageFragment_to_homeFragment)
                    findNavController().navigateUp()
                } else{
                    Toast.makeText(it.context, isSuccessful, Toast.LENGTH_SHORT).show()
                }
            }
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Step 1. Listen for fragment results
        setFragmentResultListener(TIME_REQUEST_KEY) { key, bundle ->
            bundle["time"]?.let {
                selectedTime = it as Long
                binding.timeText.text =DateFormat.format(TIME_FORMAT, Date(it))
            }
        }

        setFragmentResultListener(DATE_REQUEST_KEY) { key, bundle ->
            bundle["date"]?.let {
                selectedDate = it as Long
                binding.dateText.text = DateFormat.format(DATE_FORMAT, Date(it))
            }
        }

    }


}

