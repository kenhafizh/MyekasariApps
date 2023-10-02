package com.example.myekasari.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.myekasari.MyEkasari
import com.example.myekasari.R

import com.example.myekasari.databinding.FragmentSigninBinding
import com.example.myekasari.model.response.login.LoginResponse
import com.example.myekasari.model.response.login.User
import com.example.myekasari.ui.MainActivity
import com.example.myekasari.ui.auth.AuthActivity
import com.example.myekasari.ui.auth.AuthActivity.Companion.EXTRA_PAGE_REQUEST
import com.example.myekasari.ui.auth.AuthActivity.Companion.EXTRA_PAGE_SIGN_UP
import com.example.myekasari.ui.home.HomeFragment
import com.example.myekasari.utils.PreferencesHelper
import com.google.gson.Gson


@Suppress("DEPRECATION")
class SigninFragment : Fragment(), SigninContract.View{

    lateinit var  presenter: SigninPresenter
    var progressDialog : Dialog? = null
    private var _binding: FragmentSigninBinding? = null
    private val  binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = SigninPresenter(this)


        var user = PreferencesHelper.getUser()
        var userResponse = Gson().fromJson(user, User::class.java)
            if (userResponse != null) {
                val home = Intent(activity, MainActivity::class.java)
                startActivity(home)
                activity?.finish()
            }


        //initDummy()
        initView()



        binding?.apply {
            btnSignup.setOnClickListener{
                val intent = Intent(context, AuthActivity::class.java)
                intent.putExtra(EXTRA_PAGE_REQUEST, EXTRA_PAGE_SIGN_UP)
                startActivity(intent)
            }
            btnSignin.setOnClickListener{

                var email = etEmail.text.toString()
                var password = etPassword.text.toString()

                if (email.isNullOrEmpty()) {
                    etEmail.error="Silahkan masukan email anda"
                    etEmail.requestFocus()
                }else if (password.isNullOrEmpty()) {
                    etPassword.error="Silahkan masukan password anda"
                    etEmail.requestFocus()
                } else {
                    presenter.submitLogin(email,password)
                }
            }
            btnSkip.setOnClickListener {

                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {

        PreferencesHelper.setToken(loginResponse.access_token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        PreferencesHelper.setUser(json)

        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()
    }

    private fun initView(){
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let{
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
    }

    //private fun initDummy() {
     //   binding?.etEmail?.setText("admin@gmail.com")
     //   binding?.etPassword?.setText("Admin12345")
   // }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()

    }
}