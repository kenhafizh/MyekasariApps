package com.example.myekasari.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myekasari.R
import com.example.myekasari.databinding.FragmentPaymentSuccessBinding
import com.example.myekasari.ui.MainActivity
import com.example.myekasari.ui.order.OrderFragment
import com.example.myekasari.ui.order.OrderPresenter

class PaymentSuccessFragment : Fragment() {

    private lateinit var binding: FragmentPaymentSuccessBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaymentSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as DetailActivity).toolbarDetail()

        binding.btnOtherFood.setOnClickListener {
            requireActivity().finish()
        }
    }

}