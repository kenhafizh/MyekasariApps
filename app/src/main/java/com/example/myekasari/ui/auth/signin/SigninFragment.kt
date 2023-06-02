package com.example.myekasari.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myekasari.R

import com.example.myekasari.databinding.FragmentSigninBinding
import com.example.myekasari.model.response.login.LoginResponse
import com.example.myekasari.ui.MainActivity
import com.example.myekasari.ui.auth.AuthActivity
import com.example.myekasari.ui.auth.AuthActivity.Companion.EXTRA_PAGE_REQUEST
import com.example.myekasari.ui.auth.AuthActivity.Companion.EXTRA_PAGE_SIGN_UP


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

        initView()

        binding?.apply {
            btnSignup.setOnClickListener{
                val intent = Intent(context, AuthActivity::class.java)
                intent.putExtra(EXTRA_PAGE_REQUEST, EXTRA_PAGE_SIGN_UP)
                startActivity(intent)
            }
            btnSignin.setOnClickListener{

                presenter.submitLogin("admin@gmail.com","Admin12345")

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
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

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()

    }
}