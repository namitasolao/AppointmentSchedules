package com.namita.mynotepad.homepage

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
//import androidx.databinding.DataBindingUtil.inflate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.namita.mynotepad.database.ApptDatabase
import com.namita.mynotepad.R
import com.namita.mynotepad.apptpage.ApptViewModelFactory
import com.namita.mynotepad.database.Appointments
import com.namita.mynotepad.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var homeViewModel: HomeViewModel
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //FragmentHomeBinding is automatically created
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val datasource = ApptDatabase.getInstance(application).appointmentsDAO

        //val modelFactory = HomeViewModelFactory(datasource,application)
        val modelFactory = ApptViewModelFactory (datasource,application)

        homeViewModel = ViewModelProvider(this,modelFactory).get(HomeViewModel::class.java)
//        binding.homeViewModel = homeViewModel

        binding.lifecycleOwner = this

//        binding.homeViewModel?.let {
//            binding.dataText.text = it.appointments.value?.details
//        }

//        binding.dataText.text = binding.homeViewModel.appointments.value?.details

//        binding.homeViewModel.appointments.observe(this, Observer {
//            binding.dataText.text = it.toString()
//        })

        binding.addButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_apptPageFragment)
        }

        binding.deleteButton.setOnClickListener {
            homeViewModel.onDelete()
            //deleteButtonStatus()
            Handler().postDelayed({homeViewModel.initializeAppointments()}, 500L)

        }

        val adapter = AppointmentAdapter(homeViewModel.checkedAppointmentId)
        binding.apptList.adapter = adapter

        homeViewModel.appointments.observe(viewLifecycleOwner, Observer {
            it?.let {
                //adapter.data = it
                adapter.submitList(it)     //to check the difference between old and new list

                deleteButtonStatus(it.isEmpty())

            }
        })


        return binding.root
    }

    override fun onStart() {
        super.onStart()
        //deleteButtonStatus()
        homeViewModel.initializeAppointments()
    }

    private fun deleteButtonStatus(isEmpty: Boolean){
        //val count = homeViewModel.rowsExist()
        binding.deleteButton.isVisible = !isEmpty

    }
}