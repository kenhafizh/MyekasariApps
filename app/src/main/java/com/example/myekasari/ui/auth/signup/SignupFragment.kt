package com.example.myekasari.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.myekasari.R
import com.example.myekasari.databinding.FragmentSignupAddressBinding
import com.example.myekasari.databinding.FragmentSignupBinding
import com.example.myekasari.ui.auth.AuthActivity
import com.example.myekasari.ui.auth.AuthActivity.Companion.EXTRA_PAGE_REQUEST
import com.example.myekasari.ui.auth.AuthActivity.Companion.EXTRA_PAGE_SIGN_UP
import com.example.myekasari.ui.auth.AuthActivity.Companion.EXTRA_PAGE_SIGN_UP_ADDRESS

@Suppress("DEPRECATION")
class SignupFragment : Fragment() {

    private var _binding : FragmentSignupBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            btnContinue.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_signup_address, null)

                (activity as AuthActivity).toolbarSignUpAddress()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}