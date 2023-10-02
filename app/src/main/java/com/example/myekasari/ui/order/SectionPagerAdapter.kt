package com.example.myekasari.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myekasari.model.response.transaction.Data
import com.example.myekasari.ui.order.inprogress.InProgressFragment
import com.example.myekasari.ui.order.pastorders.PastOrdersFragment

class SectionPagerAdapter (fm:FragmentManager, var inProgressList:ArrayList<Data>?, var pastOrderList:ArrayList<Data>?) : FragmentStatePagerAdapter(fm){

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "In Progress"
//            1 -> "Past Orders"
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
                fragment = InProgressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inProgressList)
                fragment.arguments = bundle
                return fragment
            }
           else -> {
                fragment = PastOrdersFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", pastOrderList)
                fragment.arguments = bundle
                return fragment
            }
        }
    }
}