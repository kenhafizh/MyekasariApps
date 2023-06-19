package com.example.myekasari.ui.auth.signup

import android.net.Uri
import com.example.myekasari.base.BasePresenter
import com.example.myekasari.base.BaseView
import com.example.myekasari.model.request.RegisterRequest
import com.example.myekasari.model.response.login.LoginResponse
import java.nio.file.Path

interface SignupContract {

    interface View: BaseView {
        fun onRegisterSuccess(loginResponse: LoginResponse, view:android.view.View)
        fun onRegisterPhotoSuccess(view:android.view.View)
        fun onRegisterFailed(message:String)
    }

    interface Presenter : SignupContract, BasePresenter {
        fun submitRegister(registerRequest: RegisterRequest, view:android.view.View)
        fun submitPhotoRegister(filePath:Uri, view:android.view.View)
    }
}