package com.example.myekasari.ui.auth.signup

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.myekasari.MyEkasari
import com.example.myekasari.R
import com.example.myekasari.databinding.FragmentSignupAddressBinding
import com.example.myekasari.model.request.RegisterRequest
import com.example.myekasari.model.response.login.LoginResponse
import com.example.myekasari.ui.auth.AuthActivity
import com.example.myekasari.utils.PreferencesHelper
import com.google.gson.Gson

@Suppress("DEPRECATION")
class SignupAddressFragment : Fragment(),  SignupContract.View {

    private lateinit var data:RegisterRequest
    lateinit var presenter : SignupPresenter
    var progressDialog: Dialog?=null

    private lateinit var _binding : FragmentSignupAddressBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentSignupAddressBinding.inflate(inflater, container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = SignupPresenter(this)
        data = arguments?.getParcelable<RegisterRequest>("data")!!

//        initDummy()
        initListener()
        initView()
    }

    private fun initListener() {


        binding?.apply {
            btnSignUpNow.setOnClickListener {

                var phone = binding.etPhoneNumber.text.toString()
                var address = binding.etAddress.text.toString()
                var houseNumber = binding.etHouseNumber.text.toString()
                var city = binding.etCity.text.toString()

                data.let {
                    it.address = address
                    it.city = city
                    it.houseNumber = houseNumber
                    it.phoneNumber = phone
                }


                if (phone.isNullOrEmpty()) {
                    binding.etPhoneNumber.error = "Masukkan Nomor HP anda"
                    binding.etPhoneNumber.requestFocus()
                } else if (address.isNullOrEmpty()) {
                    binding.etAddress.error = "Masukkan Alamat anda"
                    binding.etAddress.requestFocus()
                } else if (houseNumber.isNullOrEmpty()) {
                    binding.etHouseNumber.error = "Masukkan Nomor Rumah anda"
                    binding.etHouseNumber.requestFocus()
                } else if (city.isNullOrEmpty()) {
                    binding.etCity.error = "Masukkan Kota anda"
                    binding.etCity.requestFocus()
                } else {
                    presenter.submitRegister(data, it)
//                    presenter.submitPhotoRegister(data.filePath!!, it)
                }

            }
        }
    }

//    private fun initDummy() {
//        binding.etPhoneNumber.setText("08888888")
//        binding.etAddress.setText("Jalan Kentang")
//        binding.etHouseNumber.setText("155")
//        binding.etCity.setText("Kebumen")
//    }


    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) {

        PreferencesHelper.setToken(loginResponse.access_token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        PreferencesHelper.setUser(json)

        if (data.filePath == null) {
            Navigation.findNavController(view)
                .navigate(R.id.action_signup_success, null)
            (activity as AuthActivity).toolbarSignUpSuccess()
        } else {
//            presenter.submitPhotoRegister(data.filePath!!, view)
        }
    }

    override fun onRegisterPhotoSuccess(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_signup_success, null)
        (activity as AuthActivity).toolbarSignUpSuccess()
    }
    override fun onRegisterFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

        private fun initView() {
            progressDialog = Dialog(requireContext())
            val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

            progressDialog?.let{
                it.setContentView(dialogLayout)
                it.setCancelable(false)
                it.window?.setBackgroundDrawableResource(android.R.color.transparent)
            }
        }


    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}