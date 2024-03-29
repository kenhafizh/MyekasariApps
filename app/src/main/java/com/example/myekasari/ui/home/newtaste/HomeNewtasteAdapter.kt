package com.example.myekasari.ui.home.newtaste

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myekasari.R
import com.example.myekasari.model.dummy.HomeVerticalModel
import com.example.myekasari.model.response.home.Data
import com.example.myekasari.utils.Helpers.formatPrice

class HomeNewtasteAdapter (

    private val listData : List<Data>,
    private val itemAdapterCallback : ItemAdapterCallback,
) : RecyclerView.Adapter<HomeNewtasteAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_vertical, parent, false)
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
        fun bind(data: Data, itemAdapterCallback: ItemAdapterCallback){
            itemView.apply {
                val tvTitle = findViewById<TextView>(R.id.tvTitle)
                val tvPrice = findViewById<TextView>(R.id.tvPrice)
                val rBar = findViewById<RatingBar>(R.id.rbFood)
                val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)

                tvTitle.text = data.name
                tvPrice.formatPrice(data.price.toString())
                rBar.rating //kira-kira perlu ada atau tidak tanyakan ke bapak dosen


                Glide.with(context)
                    .load(data.picturePath)
                    .into(ivPoster)


                itemView.setOnClickListener {itemAdapterCallback.onClick(it, data)}
            }
        }

    }

    interface ItemAdapterCallback {
        fun onClick(v : View, data:Data)
    }
}