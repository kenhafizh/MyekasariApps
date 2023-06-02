package com.example.myekasari.ui.profile.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.myekasari.R
import com.example.myekasari.databinding.FragmentProfileAccountBinding
import com.example.myekasari.model.dummy.ProfileMenuModel
import com.example.myekasari.ui.profile.ProfileMenuAdapter

@Suppress("DEPRECATION")
class ProfileAccountFragment : Fragment(), ProfileMenuAdapter.ItemAdapterCallback {

    private var menuArrayList: ArrayList<ProfileMenuModel> = ArrayList()
    private var _binding: FragmentProfileAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileAccountBinding.inflate(inflater, container,false)
        val root: View = binding.root
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDataDummy()

        var adapter = ProfileMenuAdapter(menuArrayList, this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.rcList.layoutManager = layoutManager
        binding.rcList.adapter = adapter
    }
    fun initDataDummy(){
        menuArrayList = ArrayList()
        menuArrayList.add(ProfileMenuModel("Edit Profile"))
        menuArrayList.add(ProfileMenuModel("Home Address"))
        menuArrayList.add(ProfileMenuModel("Security"))
        menuArrayList.add(ProfileMenuModel("Payment"))
    }

    override fun onClick(v: View, data: ProfileMenuModel) {
        Toast.makeText(context,"ini menu yang kamu klik" +data.title, Toast.LENGTH_SHORT).show()
    }
}
