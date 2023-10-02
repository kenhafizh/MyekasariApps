package com.example.myekasari.ui.order

import com.example.myekasari.base.BasePresenter
import com.example.myekasari.base.BaseView
import com.example.myekasari.model.response.transaction.TransactionResponse

interface OrderContract {

    interface View: BaseView {
        fun onTransactionSuccess(transactionResponse: TransactionResponse)
        fun onTransactionFailed(message:String)
    }

    interface Presenter : OrderContract, BasePresenter {
        fun getTransaction()
    }
}