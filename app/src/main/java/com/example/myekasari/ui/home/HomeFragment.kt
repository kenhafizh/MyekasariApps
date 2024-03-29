package com.example.myekasari.ui.home

import android.app.Dialog
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
import com.bumptech.glide.Glide
import com.example.myekasari.MyEkasari
import com.example.myekasari.R
import com.example.myekasari.databinding.FragmentHomeBinding
import com.example.myekasari.model.dummy.HomeModel
import com.example.myekasari.model.response.home.Data
import com.example.myekasari.model.response.home.HomeResponse
import com.example.myekasari.model.response.login.User
import com.example.myekasari.ui.detail.DetailActivity
import com.example.myekasari.utils.PreferencesHelper
import com.google.gson.Gson

@Suppress("DEPRECATION")
class HomeFragment : Fragment() , HomeAdapter.ItemAdapterCallback, HomeContract.View {

    private var newStateList : ArrayList<Data> = ArrayList()
    private var popularList : ArrayList<Data> = ArrayList()
    private var recommendedList : ArrayList<Data> = ArrayList()
    private lateinit var presenter: HomePresenter

    var progressDialog : Dialog? = null

    private var foodList : ArrayList<Data> = ArrayList()
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

        initView()
        presenter = HomePresenter(this)
        presenter.getHome()
        //initDataDummy()

    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let{
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        var user = PreferencesHelper.getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

//        if (!userResponse.profile_photo_url.isNullOrEmpty()) {
//            Glide.with(requireActivity())
//                .load(userResponse.profile_photo_url)
//                .into(binding.ivProfile)
//        }
    }

//    fun initDataDummy(){
//        foodList = ArrayList()
//        foodList.add(HomeModel("Nasi Tapir","",5f))
//        foodList.add(HomeModel("Pepes Kucing","",4f))
//        foodList.add(HomeModel("Kikil Banteng","",4.5f))
//    }

    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }

    override fun onHomeSuccess(homeResponse: HomeResponse) {

        for (a in homeResponse.data.indices) {
            var items:List<String> = homeResponse.data[a].types?.split(",") ?: ArrayList()
            for (x in items.indices) {
                if (items[x].equals("new_food", true)) {
                    newStateList?.add(homeResponse.data[a])
                } else if (items[x].equals("recommended", true)) {
                    recommendedList?.add(homeResponse.data[a])
                } else if (items[x].equals("popular", true)) {
                    popularList?.add(homeResponse.data[a])
                }
            }

        }


        var adapter = HomeAdapter(homeResponse.data, this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        binding.rcList.layoutManager = layoutManager
        binding.rcList.adapter = adapter

        val sectionPagerAdapter = SectionPagerAdapter(
            childFragmentManager
        )
        sectionPagerAdapter.setData(newStateList,popularList,recommendedList)
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun onHomeFailed(message: String) {
        Toast.makeText(context, message,Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }


}