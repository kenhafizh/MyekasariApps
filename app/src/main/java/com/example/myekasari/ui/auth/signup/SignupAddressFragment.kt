package com.example.myekasari.ui.auth.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.myekasari.R
import com.example.myekasari.databinding.FragmentSignupAddressBinding
import com.example.myekasari.ui.auth.AuthActivity

class SignupAddressFragment : Fragment() {

    private var _binding : FragmentSignupAddressBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        _binding = FragmentSignupAddressBinding.inflate(inflater, container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            btnSignUpNow.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_signup_success, null)

                (activity as AuthActivity).toolbarSignUpSuccess()
            }
        }

    }
}