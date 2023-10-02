package com.example.myekasari.ui.order.inprogress

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myekasari.databinding.FragmentInprogressBinding
import com.example.myekasari.model.response.transaction.Data
import com.example.myekasari.ui.order.detailsorders.OrdersDetailActivity


@Suppress("DEPRECATION")
class InProgressFragment : Fragment(), InProgressAdapter.ItemAdapterCallback {

    private lateinit var binding: FragmentInprogressBinding
    private var adapter:InProgressAdapter?=null
    var inProgressList:ArrayList<Data>? =  ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInprogressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inProgressList = arguments?.getParcelableArrayList("data")

        if (!inProgressList.isNullOrEmpty()) {
            adapter = InProgressAdapter(inProgressList!!, this)
            val layoutManager = LinearLayoutManager(activity)
            binding.rcList.layoutManager = layoutManager
            binding.rcList.adapter = adapter
        }
    }

    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, OrdersDetailActivity::class.java). putExtra("data", data)
        startActivity(detail)
    }

}
