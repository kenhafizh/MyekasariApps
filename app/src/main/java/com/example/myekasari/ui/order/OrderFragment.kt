package com.example.myekasari.ui.order

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myekasari.R

import com.example.myekasari.databinding.FragmentOrderBinding
import com.example.myekasari.model.response.transaction.Data
import com.example.myekasari.model.response.transaction.TransactionResponse

//
class OrderFragment : Fragment(), OrderContract.View {

    private lateinit var binding: FragmentOrderBinding


    lateinit var presenter: OrderPresenter
    var progressDialog: Dialog? = null

    val inProgressList: ArrayList<Data>? = ArrayList()
    val pastOrdersList: ArrayList<Data>? = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        presenter = OrderPresenter(this)
        presenter.getTransaction()
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        binding.includeToolbar.toolbar2.title = "Your Orders"
        binding.includeToolbar.toolbar2.subtitle = "Pesanan Produk Ekasari anda"
    }

    override fun onTransactionSuccess(transactionResponse: TransactionResponse) {
        if (transactionResponse.data.isNullOrEmpty()) {
            binding.llEmpty.visibility = View.VISIBLE
            binding.llTab.visibility = View.GONE
            binding.includeToolbar.toolbar2.visibility = View.GONE
        } else {

            for (a in transactionResponse.data.indices) {
                if (transactionResponse.data[a]?.status.equals("ON_DELIVERY", true)
                    || transactionResponse.data[a]?.status.equals("PENDING", true)
                ) {
                    inProgressList?.add(transactionResponse.data[a]!!)
                } else if (transactionResponse.data[a]?.status.equals("DELIVERY", true)
                    || transactionResponse.data[a]?.status.equals("CANCELLED", true)
                    || transactionResponse.data[a]?.status.equals("SUCCESS", true)
                ) {
                    pastOrdersList?.add(transactionResponse.data[a]!!)
                }
            }


                val sectionPagerAdapter = SectionPagerAdapter(
                    childFragmentManager,
                    inProgressList, pastOrdersList
                )
                binding.viewPager.adapter = sectionPagerAdapter
                binding.tabLayout.setupWithViewPager(binding.viewPager)

            }
    }

        override fun onTransactionFailed(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        override fun showLoading() {
            progressDialog?.show()
        }

        override fun dismissLoading() {
            progressDialog?.dismiss()
        }

    }
