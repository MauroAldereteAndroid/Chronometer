package com.maurodev.chronometer.ui.principal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maurodev.chronometer.R

class RecyclerViewAdapter(private val listTimes: MutableList<String>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewCircle: TextView

        init {
            textViewCircle = view.findViewById(R.id.tv_item_recycler_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_view_item_cicle, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount() = listTimes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var positionLoop = (position + 1).toString()
        holder.textViewCircle.text = "$positionLoop. " +listTimes[position]

    }
}