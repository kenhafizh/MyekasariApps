package com.example.myekasari.ui.home

import com.example.myekasari.base.BasePresenter
import com.example.myekasari.base.BaseView
import com.example.myekasari.model.response.home.HomeResponse
import com.example.myekasari.model.response.login.LoginResponse

interface HomeContract {

    interface View: BaseView {
        fun onHomeSuccess(homeResponse: HomeResponse)
        fun onHomeFailed(message:String)
    }

    interface Presenter : HomeContract, BasePresenter {
        fun getHome()
    }
}