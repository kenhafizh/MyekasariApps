package com.example.myekasari.ui.order.pastorders


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


class PastOrdersAdapter(


    private val listData: List<Data>,
    private val itemAdapterCallback: ItemAdapterCallback,
    ) : RecyclerView.Adapter<PastOrdersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_pastorder, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Data, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                val tvTitle = findViewById<TextView>(R.id.tvTitle)
                val ivPoster = findViewById<ImageView>(R.id.ivPoster)
                val tvPrice = findViewById<TextView>(R.id.tvPrice)
                val tvDate = findViewById<TextView>(R.id.tvDate)

                tvTitle.text = data.food?.name
                tvPrice.formatPrice(data.food?.price.toString())
                tvDate.text = data.food?.createdAt

                val requestOptions = RequestOptions()
                    .override(250, 250)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)

                Glide.with(itemView.context)
                    .load(data.food?.picturePath)
                    .apply(requestOptions)
                    .into(ivPoster)

                if (data.status.equals("CANCELLED", true)) {
                    val tvCancelled = itemView.findViewById<TextView>(R.id.tvCancelled)
                    tvCancelled.visibility = View.VISIBLE
                }

                itemView.setOnClickListener { itemAdapterCallback.onClick(itemView, data) }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data:Data)
    }
}