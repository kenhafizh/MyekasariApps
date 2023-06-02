package com.example.myekasari.ui.profile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myekasari.R
import com.example.myekasari.model.dummy.ProfileMenuModel

class ProfileMenuAdapter (

    private val listData : List<ProfileMenuModel>,
    private val itemAdapterCallback : ItemAdapterCallback,
) : RecyclerView.Adapter<ProfileMenuAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileMenuAdapter.ViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_menu_profile, parent, false)
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
        fun bind(data: ProfileMenuModel, itemAdapterCallback: ItemAdapterCallback){
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
        fun onClick(v : View, data:ProfileMenuModel)
    }
}