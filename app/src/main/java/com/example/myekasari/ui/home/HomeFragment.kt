package com.example.myekasari.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myekasari.databinding.FragmentHomeBinding
import com.example.myekasari.model.dummy.HomeModel
import com.example.myekasari.ui.detail.DetailActivity

@Suppress("DEPRECATION")
class HomeFragment : Fragment() , HomeAdapter.ItemAdapterCallback {

    private var foodList : ArrayList<HomeModel> = ArrayList()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDataDummy()
        var adapter = HomeAdapter(foodList, this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        binding.rcList.layoutManager = layoutManager
        binding.rcList.adapter = adapter

        val sectionPagerAdapter = SectionPagerAdapter(
            childFragmentManager
        )
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    fun initDataDummy(){
        foodList = ArrayList()
        foodList.add(HomeModel("Nasi Tapir","",5f))
        foodList.add(HomeModel("Pepes Kucing","",4f))
        foodList.add(HomeModel("Kikil Banteng","",4.5f))
    }

    override fun onClick(v: View, data: HomeModel) {
        val detail = Intent(activity, DetailActivity::class.java)
        startActivity(detail)
    }
}