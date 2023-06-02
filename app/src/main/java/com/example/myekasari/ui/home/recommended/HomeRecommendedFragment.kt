package com.example.myekasari.ui.home.recommended

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myekasari.R
import com.example.myekasari.databinding.FragmentHomeNewTasteBinding
import com.example.myekasari.model.dummy.HomeVerticalModel
import com.example.myekasari.ui.detail.DetailActivity
import com.example.myekasari.ui.home.newtaste.HomeNewtasteAdapter


class HomeRecommendedFragment : Fragment(), HomeNewtasteAdapter.ItemAdapterCallback {

    private var foodList : ArrayList<HomeVerticalModel> = ArrayList()
    private lateinit var binding : FragmentHomeNewTasteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home_new_taste, container, false)
        binding = FragmentHomeNewTasteBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home_new_taste, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDataDummy()

        val adapter = HomeNewtasteAdapter(foodList, this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.rcListV.layoutManager = layoutManager
        binding.rcListV.adapter = adapter
    }

    fun initDataDummy(){
        foodList = ArrayList()
        foodList.add(HomeVerticalModel("Nasi Tapir","10000","",5f))
        foodList.add(HomeVerticalModel("Pepes Kucing","50000","",4f))
        foodList.add(HomeVerticalModel("Kikil Banteng","15000","",4.5f))
    }

    override fun onClick(v: View, data: HomeVerticalModel) {
        val detail = Intent(activity, DetailActivity::class.java)
        startActivity(detail)
    }

}