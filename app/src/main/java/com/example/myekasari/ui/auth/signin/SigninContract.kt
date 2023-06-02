package com.example.myekasari.ui.auth.signin

import com.example.myekasari.base.BasePresenter
import com.example.myekasari.base.BaseView
import com.example.myekasari.model.response.login.LoginResponse

interface SigninContract {

    interface View: BaseView {
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message:String)
    }

    interface Presenter : SigninContract, BasePresenter {
        fun submitLogin(email:String, password:String)
    }
}