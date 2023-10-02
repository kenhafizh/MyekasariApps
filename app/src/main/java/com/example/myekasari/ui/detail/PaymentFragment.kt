package com.example.myekasari.ui.detail

import android.app.Dialog
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
import com.example.myekasari.MyEkasari
import com.example.myekasari.R
import com.example.myekasari.databinding.FragmentPaymentBinding
import com.example.myekasari.model.response.checkout.CheckoutResponse
import com.example.myekasari.model.response.login.LoginResponse
import com.example.myekasari.model.response.login.User
import com.example.myekasari.utils.Helpers.formatPrice
import com.example.myekasari.utils.PreferencesHelper
import com.google.gson.Gson

class PaymentFragment : Fragment(), PaymentContract.View{

    private var _binding : FragmentPaymentBinding? = null
    private val binding get() = _binding

    var progessDialog: Dialog?=null
    lateinit var presenter: PaymentPresenter
    var total : Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarPayment()
        
        var data = arguments?.getParcelable<com.example.myekasari.model.response.home.Data>("data")
        initView(data)
        initView()
        presenter = PaymentPresenter(this)

    }

    private fun initView() {
        progessDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader,null)

        progessDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun initView(data: com.example.myekasari.model.response.home.Data?) {
        binding?.tvTitle?.text = data?.name
        binding?.tvPrice?.formatPrice(data?.price.toString())

        Glide.with(requireContext())
            .load(data?.picturePath)
            .into(binding!!.ivPoster)

        binding?.tvNameItem?.text = data?.name
        binding?.tvHarga?.formatPrice(data?.price.toString())

        if (!data?.price.toString().isNullOrEmpty()) {
//            var totalTax = data?.price?.div(10)
//            binding?.tvTax?.formatPrice(totalTax.toString())

            total = data?.price!!+1000
            binding?.tvTotal?.formatPrice(total.toString())

        } else {
            binding?.tvPrice?.text = "RP. 0"
            binding?.tvTax?.text = "RP. 0"
            binding?.tvTotal?.text = "RP. 0"
            total = 0
        }

        var  user = PreferencesHelper.getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        binding?.tvName?.text = userResponse?.name
        binding?.tvPhoneNo?.text = userResponse?.phoneNumber
        binding?.tvAddress?.text = userResponse?.address
        binding?.tvCity?.text = userResponse?.city

        binding?.btnCheckout?.setOnClickListener {
            presenter.getCheckout(
                data?.id.toString(),
                userResponse?.id.toString(),
                "1",
                total.toString(),
                it
            )

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: View) {

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(checkoutResponse.paymentUrl)
        startActivity(i)
        Navigation.findNavController(view).navigate(R.id.action_payment_success)
    }


    override fun onCheckoutFailed(message: String) {
       Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progessDialog?.show()
    }

    override fun dismissLoading() {
        progessDialog?.dismiss()

    }
}
