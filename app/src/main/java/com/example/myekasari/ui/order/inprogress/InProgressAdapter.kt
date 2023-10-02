package com.example.myekasari.ui.order.inprogress



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.myekasari.R


import com.example.myekasari.model.response.transaction.Data
import com.example.myekasari.utils.Helpers.formatPrice


class InProgressAdapter(


    private val listData: List<Data>,
    private val itemAdapterCallback: ItemAdapterCallback,
    ) : RecyclerView.Adapter<InProgressAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_inprogress, parent, false)
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
            itemView.apply {
                val tvTitle = findViewById<TextView>(R.id.tvTitle)
                val ivPoster = findViewById<ImageView>(R.id.ivPoster)
                val tvPrice = findViewById<TextView>(R.id.tvPrice)

                tvTitle.text = data.food!!.name
                tvPrice.formatPrice(data.food!!.price.toString())


                val requestOptions = RequestOptions()
                    .override(250, 250)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)

                Glide.with(itemView.context)
                    .load(data.food?.picturePath)
                    .apply(requestOptions)
                    .into(ivPoster)


                itemView.setOnClickListener { itemAdapterCallback.onClick(itemView, data) }
            }
        }
    }
    interface ItemAdapterCallback {
        fun onClick(v: View, data: Data)
    }
}