package com.namita.mynotepad.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
//import com.namita.mynotepad.TextItemViewHolder
import com.namita.mynotepad.database.Appointments
import com.namita.mynotepad.databinding.ListItemAppointmentBinding

class AppointmentAdapter : ListAdapter<Appointments,AppointmentAdapter.ViewHolder>(AppointmentsDiffCallback()) {
//    var data = listOf<Appointments>()
//    set(value) {
//        field = value
//        notifyDataSetChanged()
//    }

//    override fun getItemCount(): Int {
//        return data.size
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        //val item = data[position]

        //holder.textView.text = item.details.toString()

        //val res = holder.itemView.context.resources
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



    class ViewHolder private constructor(val binding: ListItemAppointmentBinding) :RecyclerView.ViewHolder(binding.root){

        fun bind(item: Appointments) {
//            binding.detailsTextView.text = item.details
//            binding.dateTextView.text = item.apptDate
//            binding.timeTextView.text = item.apptTime

            binding.appointment = item
            binding.executePendingBindings()// This call is an optimization that asks data binding to execute any
                                            // pending bindings right away. It's always a good idea to call
                                            //executePendingBindings() when you use binding adapters in a
                                            // RecyclerView, because it can slightly speed up sizing the views.
        }

        companion object {
             fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
//                val view = layoutInflater.inflate(R.layout.list_item_appointment, parent, false)

                 val binding = ListItemAppointmentBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
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