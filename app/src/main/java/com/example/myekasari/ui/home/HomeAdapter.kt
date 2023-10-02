package com.example.myekasari.ui.home


import android.media.Rating
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.myekasari.R
import com.example.myekasari.RealPathUtil
import com.example.myekasari.model.response.home.Data


class HomeAdapter(


    private val listData: List<Data>,
    private val itemAdapterCallback: ItemAdapterCallback,
    ) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Data, itemAdapterCallback: ItemAdapterCallback) {
            val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
            val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
//            val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)

            tvTitle.text = data.name
//            ratingBar.rating = data.rate?.toFloat() ?: 0f

//
            val requestOptions = RequestOptions()
//                .override(250,250)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)

            Glide.with(itemView.context)
                .load(data.picturePath)
                .apply(requestOptions)
                .into(ivPoster)


            itemView.setOnClickListener { itemAdapterCallback.onClick(itemView, data) }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data: Data)
    }
}