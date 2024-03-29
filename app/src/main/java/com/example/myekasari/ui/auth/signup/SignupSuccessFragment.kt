package com.example.myekasari.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myekasari.R
import com.example.myekasari.databinding.FragmentSignupSuccessBinding
import com.example.myekasari.ui.MainActivity

@Suppress("DEPRECATION")
class SignupSuccessFragment : Fragment() {

    lateinit var binding: FragmentSignupSuccessBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_signup_success, container, false)
        binding = FragmentSignupSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

       binding.buttonFind.setOnClickListener {
            val home = Intent (activity, MainActivity::class.java)
            startActivity(home)
            activity?.finishAffinity()
        }
    }

}