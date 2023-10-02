package com.example.myekasari.ui.order.detailsorders

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.myekasari.R
import com.example.myekasari.databinding.ActivityDetailOrdersBinding



class OrdersDetailActivity : AppCompatActivity() {
     lateinit var binding: ActivityDetailOrdersBinding

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_orders)

        intent.extras?.let {
            val navController = Navigation.findNavController(findViewById(R.id.detailOrdersHostFragment))
            val bundle = Bundle()
            bundle.putParcelable("data", it.get("data") as Parcelable?)
            navController.setGraph(navController.graph, bundle)
        }
    }

    fun toolbarPayment() {
        binding.includeToolbar.toolbar2.visibility = View.VISIBLE
        binding.includeToolbar.toolbar2.title = "Payment"
        binding.includeToolbar.toolbar2.subtitle = "You deserve better meal"
        binding.includeToolbar.toolbar2.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000)
        binding.includeToolbar.toolbar2.setNavigationOnClickListener { onBackPressed() }
    }
}