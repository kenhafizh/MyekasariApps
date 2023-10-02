 package com.example.myekasari.ui.order.pastorders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myekasari.R
import com.example.myekasari.databinding.FragmentPastorderBinding
import com.example.myekasari.model.response.transaction.Data


 @Suppress("DEPRECATION")
class PastOrdersFragment : Fragment(), PastOrdersAdapter.ItemAdapterCallback {

    private lateinit var binding: FragmentPastorderBinding
    private var adapter:PastOrdersAdapter?=null
    var pastordersList:ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pastorder, container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pastordersList = arguments?.getParcelableArrayList("data")

        if (!pastordersList.isNullOrEmpty()) {
            adapter = PastOrdersAdapter(pastordersList!!,this)
            val layoutManager = LinearLayoutManager(activity)
            binding.rcList.layoutManager = layoutManager
            binding.rcList.adapter = adapter
        }
    }

     override fun onClick(v: View, data: Data) {
         Toast.makeText(activity,"coba klik", Toast.LENGTH_LONG).show()

     }
 }
