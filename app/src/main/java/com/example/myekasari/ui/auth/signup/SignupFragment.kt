package com.example.myekasari.ui.auth.signup

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myekasari.R
import com.example.myekasari.databinding.FragmentSignupBinding
import com.example.myekasari.model.request.RegisterRequest
import com.example.myekasari.ui.auth.AuthActivity
import com.github.dhaval2404.imagepicker.ImagePicker

@Suppress("DEPRECATION")
class SignupFragment : Fragment() {

    var filePath: Uri? = null
    private lateinit var _binding : FragmentSignupBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        initDummy()
        initListener()


    }

    private fun initListener() {
        _binding?.ivProfil?.setOnClickListener{
            ImagePicker.with(this)
                .cameraOnly()
                .start()
        }

        binding?.apply {
            btnContinue.setOnClickListener {
                var fullname = etFullName.text.toString()
                var email = etEmail.text.toString()
                var pass = etPassword.text.toString()

                if(fullname.isNullOrEmpty()) {
                    etFullName.error = "Silahkan masukkan fullaname"
                    etFullName.requestFocus()
                } else if(email.isNullOrEmpty()) {
                    etEmail.error = "Silahkan masukkan email"
                    etEmail.requestFocus()
                } else if(pass.isNullOrEmpty()) {
                    etPassword.error = "Silahkan masukkan password"
                    etPassword.requestFocus()
                }else {
                    var data = RegisterRequest(
                        fullname,
                        email,
                        pass,
                        pass,
                        "","","","",
                        filePath
                    )

                    var bundle = Bundle()
                    bundle.putParcelable("data", data)

                    Navigation.findNavController(it)
                        .navigate(R.id.action_signup_address, bundle)

                    (activity as AuthActivity).toolbarSignUpAddress()
                }
                }



        }
    }

//    private fun initDummy(){
//        binding.etFullName.setText("Ken Hafizh")
//        binding.etEmail.setText("admin@gmail.com")
//        binding.etPassword.setText("Admin12345")
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            filePath = data?.data!!

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivProfil)
        } else if (resultCode == ImagePicker.RESULT_ERROR){
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {

            Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }



    }
    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null
    }
}