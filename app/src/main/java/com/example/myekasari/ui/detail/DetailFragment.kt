package com.example.myekasari.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.myekasari.R
import com.example.myekasari.databinding.FragmentDetailBinding
import com.example.myekasari.model.response.home.Data
import com.example.myekasari.utils.Helpers.formatPrice


class DetailFragment : Fragment() {


    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding

    var bundle : Bundle ?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarDetail()

        arguments?.let {
            DetailFragmentArgs.fromBundle(it).data.let { it ->
                initView(it)
            }
        }

        binding?.apply {
            btnOrderNow.setOnClickListener{
                Navigation.findNavController(it)
                    .navigate(R.id.action_payment, bundle)
            }

        }

    }

    private fun initView(data: Data?) {

        bundle = bundleOf("data" to data)

        Glide.with(requireContext())
            .load(data?.picturePath)
            .into(binding!!.ivPoster)

        binding!!.tvTitle.text = data?.name
        binding!!.tvDesc.text = data?.description
        binding!!.tvIngredients.text = data?.ingredients

        if (data != null) {
            binding!!.tvTotal.formatPrice(data.price.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}