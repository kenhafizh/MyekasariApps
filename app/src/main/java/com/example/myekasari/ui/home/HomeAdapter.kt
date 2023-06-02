package com.example.myekasari.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.bumptech.glide.Glide
import com.example.myekasari.R
import com.example.myekasari.databinding.ItemHomeHorizontalBinding
import com.example.myekasari.model.dummy.HomeModel
import org.w3c.dom.Text

class HomeAdapter (

    private val listData : List<HomeModel>,
    private val itemAdapterCallback : ItemAdapterCallback,
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_horizontal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }



    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder (itemView:View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("WrongViewCast")
        fun bind(data: HomeModel, itemAdapterCallback: ItemAdapterCallback){
            itemView.apply {
                var tvTitle = findViewById<TextView>(R.id.tvTitle)

                tvTitle.text = data.title



                //Glide.with(context)
                //    .load(data.src)


                itemView.setOnClickListener {itemAdapterCallback.onClick(it, data)}
            }
        }

    }

    interface ItemAdapterCallback {
        fun onClick(v : View, data:HomeModel)
    }
}