package com.example.myekasari.ui.detail

import com.example.myekasari.base.BasePresenter
import com.example.myekasari.base.BaseView
import com.example.myekasari.model.response.checkout.CheckoutResponse


interface PaymentContract {

    interface View: BaseView {
        fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: android.view.View)
        fun onCheckoutFailed(message:String)
    }

    interface Presenter : PaymentContract, BasePresenter {
        fun getCheckout(FoodId:String, userId:String, quantity:String, total:String, view: android.view.View)
    }
}