package com.example.myekasari.ui.order.detailsorders

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.bumptech.glide.Glide
import com.example.myekasari.R
import com.example.myekasari.databinding.FragmentOrdersDetailBinding
import com.example.myekasari.model.response.transaction.Data
import com.example.myekasari.ui.detail.DetailFragmentArgs
import com.example.myekasari.utils.Helpers.formatPrice



class OrdersDetailFragment : Fragment(), OrdersDetailContract.View {

    private var _binding : FragmentOrdersDetailBinding? = null
    private val binding get() = _binding
    var progressDialog: Dialog? = null
    lateinit var presenter : OrdersDetailPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_orders_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            OrdersDetailFragmentArgs.fromBundle(it).data.let {
                initView(it)
            }

        }

        (activity as OrdersDetailActivity?)!!.toolbarPayment()
        initView()
        presenter = OrdersDetailPresenter(this)

    }

    private fun initView(data: Data?) {
        binding?.tvTitle?.text = data?.food?.name
        binding?.tvPrice?.formatPrice(data?.food?.price.toString())
        Glide.with(requireContext())
            .load(data?.food?.picturePath)
            .into(binding!!.ivPoster)

        binding?.tvNameItem?.text = data?.food?.name
        binding?.tvHarga?.formatPrice(data?.food?.price.toString())

        if (!data?.food?.price.toString().isNullOrEmpty()) {
//            var totalTax = data?.food?.price?.div(10)
//            tvTax.formatPrice(totalTax.toString())

            var total = data?.food?.price!! + 50000
            binding?.tvTotal?.formatPrice(total.toString())
        } else {
            binding?.tvPrice?.text = "IDR. 0"
//            tvTax.text = "IDR. 0"
            binding?.tvTotal?.text = "IDR. 0"
        }

       binding?.tvNama?.text = data?.user?.name
        binding?.tvPhone?.text = data?.user?.phoneNumber
        binding?.tvAddress?.text = data?.user?.address
        binding?.tvHouseNo?.text = data?.user?.houseNumber
        binding?.tvCity?.text = data?.user?.city

        binding?.tvOrderStatus?.text = data?.id.toString()

        if (data?.status.equals("ON_DELIVERY", true)) {
            binding?.btnCancelled?.visibility = View.VISIBLE
            binding?.constraintLayout3?.visibility = View.VISIBLE
            binding?.tvPending?.text = "Paid"
        } else if (data?.status.equals("SUCCESS", true)) {
            binding?.btnCancelled?.visibility = View.INVISIBLE
            binding?.constraintLayout3?.visibility = View.VISIBLE
            binding?.tvPending?.text = "Paid"
        } else if (data?.status.equals("PENDING", true)) {
            binding?.btnCancelled?.visibility = View.VISIBLE
            binding?.btnCancelled?.text = "Pay Now"
            binding?.constraintLayout3?.visibility = View.VISIBLE
            binding?.tvPending?.text = "Pending"
        }

        binding?.btnCancelled?.setOnClickListener {
            if (binding?.btnCancelled?.text!!.equals("Pay Now")) {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(data?.paymentUrl)
                startActivity(i)
            } else {
                presenter.getUpdateTransaction(data?.id.toString(), "CANCELLED")
            }
        }
    }

    override fun onUpdateTransactionSuccess(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        requireActivity().finish()
    }

    override fun onUpdateTransactionFailed(message: String) {
    }


    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
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