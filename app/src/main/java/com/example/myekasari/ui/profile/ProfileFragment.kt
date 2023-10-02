package com.example.myekasari.ui.profile

import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaRouter.UserRouteInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myekasari.MyEkasari
import com.example.myekasari.databinding.FragmentHomeBinding
import com.example.myekasari.databinding.FragmentProfileBinding
import com.example.myekasari.model.response.login.User
import com.example.myekasari.ui.auth.AuthActivity
import com.example.myekasari.ui.home.HomeAdapter
import com.example.myekasari.ui.profile.SectionPagerAdapter
import com.example.myekasari.utils.PreferencesHelper
import com.google.gson.Gson
import kotlin.math.sign

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val sectionPagerAdapter = SectionPagerAdapter(
            childFragmentManager
        )
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)


        var user = PreferencesHelper.getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        if (userResponse!=null){
            binding.tvName.setText(userResponse.name)
            binding.tvEmail.setText(userResponse.email)
            if (!userResponse.profile_photo_url.isNullOrEmpty()){
                Glide.with(requireActivity())
                    .load(userResponse.profile_photo_url)
                    .apply(RequestOptions.circleCropTransform())
//                    .into(binding.ivPicture)
            }
        }

        binding.btnSignout.setOnClickListener {
            PreferencesHelper.clear()
            val signout = Intent(activity, AuthActivity::class.java)
            startActivity(signout)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}