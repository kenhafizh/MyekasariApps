package com.example.myekasari.network

import com.example.myekasari.model.response.Wrapper
import com.example.myekasari.model.response.login.LoginResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface Endpoint {

    @FormUrlEncoded
    @POST ("login")
    fun login (@Field("email") email: String,
              @Field("password") password : String) : Observable<Wrapper<LoginResponse>>

    @FormUrlEncoded
    @POST ("register")
    fun register (@Field("name") name: String,
                  @Field("email") email: String,
                  @Field("password") password: String,
                  @Field("password_confirm") password_confirmation: String,
                  @Field("address") addres: String,
                  @Field("city") city: String,
                  @Field("houseNumber") houseNumber: String,
                  @Field("phoneNumber") phoneNumber : String) : Observable<Wrapper<LoginResponse>>

    @Multipart
    @POST ("user/photo")
    fun registerPhoto (@Part profileImage:MultipartBody.Part) : Observable<Wrapper<Any>>

}