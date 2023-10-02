package com.example.myekasari.network

import com.example.myekasari.model.response.Wrapper
import com.example.myekasari.model.response.checkout.CheckoutResponse
import com.example.myekasari.model.response.home.HomeResponse
import com.example.myekasari.model.response.login.LoginResponse
import com.example.myekasari.model.response.transaction.TransactionResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

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
                  @Field("address") address: String,
                  @Field("city") city: String,
                  @Field("houseNumber") houseNumber: String,
                  @Field("phoneNumber") phoneNumber : String) : Observable<Wrapper<LoginResponse>>

    @Multipart
    @POST ("user/photo")
    fun registerPhoto (@Part profileImage:MultipartBody.Part) : Observable<Wrapper<Any>>
    @GET("food")
    fun home () : Observable<Wrapper<HomeResponse>>

    @FormUrlEncoded
    @POST("checkout")
    fun checkout (@Field("food_id") food_id: String,
                  @Field("user_id") user_id: String,
                  @Field("quantity") quantity: String,
                  @Field("total") total: String,
                  @Field("status") status : String) : Observable<Wrapper<CheckoutResponse>>

    @FormUrlEncoded
    @POST("transaction/{id}")
    fun transactionUpdate(@Path(value = "id") userId:String,
                          @Field("status") status: String): Observable<Wrapper<Any>>
   @GET("transaction")
   fun transaction () : Observable<Wrapper<TransactionResponse>>
}