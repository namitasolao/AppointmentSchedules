package com.namita.mynotepad.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
//import com.namita.mynotepad.TextItemViewHolder
import com.namita.mynotepad.database.Appointments
import com.namita.mynotepad.databinding.ListItemAppointmentBinding
import com.namita.mynotepad.databinding.ListItemBinding

class AppointmentAdapter(
    private val checkedApptId: MutableLiveData<Int>
) : ListAdapter<Appointments,AppointmentAdapter.ViewHolder>(AppointmentsDiffCallback()) {
//    var data = listOf<Appointments>()
//    set(value) {
//        field = value
//        notifyDataSetChanged()
//    }

//    override fun getItemCount(): Int {
//        return data.size
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, checkedApptId)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

    }

//    public fun bind(
//        holder: ViewHolder,
//        item: Appointments
//    ) {
//        holder.apptDetails.text = item.details
//        holder.apptDate.text = item.apptDate
//        holder.apptTime.text = item.apptTime
//    }



        class ViewHolder private constructor(
            val binding: ListItemAppointmentBinding,
            private val checkedApptId: MutableLiveData<Int>
        ) :RecyclerView.ViewHolder(binding.root){

            fun bind(item: Appointments) {

                binding.appointment = item
                // This call is an optimization that asks data binding to execute any
                // pending bindings right away. It's always a good idea to call
                // executePendingBindings() when you use binding adapters in a
                // RecyclerView, because it can slightly speed up sizing the views.
                binding.executePendingBindings()

                binding.checkBox.setOnClickListener {
                    checkedApptId.value = item.apptid
                }
            }



        companion object {
             fun from(
                 parent: ViewGroup,
                 checkedApptId: MutableLiveData<Int>
             ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
//                val view = layoutInflater.inflate(R.layout.list_item_appointment, parent, false)

                 val binding = ListItemAppointmentBinding.inflate(layoutInflater,parent,false)
                 //val binding = ListItemBinding.inflate(layoutInflater,parent,false)
                 return ViewHolder(binding, checkedApptId)
            }
        }
    }
}

class AppointmentsDiffCallback : DiffUtil.ItemCallback<Appointments>(){
    override fun areItemsTheSame(oldItem: Appointments, newItem: Appointments): Boolean {
       return oldItem.apptid == newItem.apptid
    }

    override fun areContentsTheSame(oldItem: Appointments, newItem: Appointments): Boolean {
        return oldItem == newItem
    }

}