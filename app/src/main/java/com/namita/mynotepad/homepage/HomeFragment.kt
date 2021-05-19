package com.namita.mynotepad.homepage

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
//import androidx.databinding.DataBindingUtil.inflate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.namita.mynotepad.database.ApptDatabase
import com.namita.mynotepad.R
import com.namita.mynotepad.apptpage.ApptViewModelFactory
import com.namita.mynotepad.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //FragmentHomeBinding is automatically created
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val datasource = ApptDatabase.getInstance(application).appointmentsDAO

        //val modelFactory = HomeViewModelFactory(datasource,application)
        val modelFactory = ApptViewModelFactory (datasource,application)

        binding.homeViewModel = ViewModelProvider(this,modelFactory).get(HomeViewModel::class.java)

        binding.lifecycleOwner = this

//        binding.homeViewModel?.let {
//            binding.dataText.text = it.appointments.value?.details
//        }

//        binding.dataText.text = binding.homeViewModel.appointments.value?.details

//        binding.homeViewModel.appointments.observe(this, Observer {
//            binding.dataText.text = it.toString()
//        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.add_button)
        button.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_apptPageFragment)
        }
    }

}