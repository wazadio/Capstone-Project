package com.example.transconnect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.transconnect.data.Bus
import kotlinx.android.synthetic.main.bus_item.view.*
import java.util.*


class DataAdapter(private val callback: DataCallback) :

    RecyclerView.Adapter<DataAdapter.ListViewHolder>() {
    private val listData = ArrayList<Bus>()

    fun setData(data: List<Bus>?) {
        if (data == null) return
        listData.clear()
        listData.addAll(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageSrc = itemView.findViewById<ImageView>(R.id.imgbus2)
        fun bindView(data: Bus) {
            with(itemView) {
                imageSrc.setImageResource(data.bImagesrc)
                koridorbus2.text = data.bKoridor
                nomorbus2.text =  data.bNomorbus
                cardview_item.setOnClickListener {
                    callback.onItemClicked(data)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.bus_item, parent, false)
        )

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: DataAdapter.ListViewHolder, position: Int) {
        holder.bindView(listData[position])
    }

}