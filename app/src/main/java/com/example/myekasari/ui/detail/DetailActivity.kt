package com.example.myekasari.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myekasari.R
import com.example.myekasari.databinding.ActivityAuthBinding
import com.example.myekasari.databinding.ActivityDetailBinding
import com.example.myekasari.databinding.FragmentDetailBinding

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =  ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun toolbarPayment() {
        binding.includeToolbar.toolbar2.visibility = View.VISIBLE
        binding.includeToolbar.toolbar2.title = "Payment"
        binding.includeToolbar.toolbar2.subtitle = "Bayar dulu brader"
        binding.includeToolbar.toolbar2.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000, null)
        binding.includeToolbar.toolbar2.setNavigationOnClickListener { onBackPressed() }
    }
    fun toolbarDetail(){
        binding.includeToolbar.toolbar2.visibility = View.GONE
    }
}
