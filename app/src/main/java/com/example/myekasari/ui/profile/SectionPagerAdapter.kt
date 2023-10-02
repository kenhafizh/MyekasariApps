package com.example.myekasari.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myekasari.model.response.transaction.Data
import com.example.myekasari.ui.home.newtaste.HomeNewTasteFragment
import com.example.myekasari.ui.home.popular.HomePopularFragment
import com.example.myekasari.ui.home.recommended.HomeRecommendedFragment
import com.example.myekasari.ui.profile.account.ProfileAccountFragment
import com.example.myekasari.ui.profile.foodmarket.ProfileFoodmarketFragment

class SectionPagerAdapter (fm:FragmentManager) : FragmentPagerAdapter(fm){


    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Account"
            1 -> "Settings"
            else -> ""
        }
    }
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment
        return when(position) {
            0 -> {
                fragment = ProfileAccountFragment()
                return fragment
            }
           1 -> {
                fragment = ProfileFoodmarketFragment()
                return fragment
            }
            else -> {
                fragment = ProfileAccountFragment()
                return fragment
            }
        }
    }

}