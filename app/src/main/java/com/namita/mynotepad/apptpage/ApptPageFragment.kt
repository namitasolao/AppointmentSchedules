package com.namita.mynotepad.apptpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.namita.mynotepad.R
import com.namita.mynotepad.database.ApptDatabase
import com.namita.mynotepad.databinding.FragmentApptPageBinding


class ApptPageFragment : Fragment(R.layout.fragment_appt_page) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //FragmentApptPageBinding is automatically created
        val binding: FragmentApptPageBinding = DataBindingUtil.inflate(
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
            okButton.setOnClickListener {
                apptPage?.onAdd(
                    dateText.text.toString(),
                    timeText.text.toString(),
                    detailsText.text.toString()
                )
                val navBack = Navigation.findNavController(it)
                navBack.navigate(R.id.action_apptPageFragment_to_homeFragment)
            }
        }


        return binding.root
    }


}