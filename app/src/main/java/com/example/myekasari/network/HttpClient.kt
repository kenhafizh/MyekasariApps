package com.example.myekasari.network
import android.util.Log
import androidx.viewbinding.BuildConfig
import androidx.viewbinding.BuildConfig.*
import com.example.myekasari.BuildConfig.BASE_URL
import com.example.myekasari.MyEkasari
import com.example.myekasari.network.Endpoint
import com.example.myekasari.utils.Helpers
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpClient {

    private var client:Retrofit? = null
    private var endpoint:Endpoint? = null

    companion object {
        private val mInstance: HttpClient = HttpClient()
        @Synchronized
        fun getInstance():HttpClient {
            return mInstance
        }
    }

    init {
        buildRetrofitClient()
    }

    fun getApi() : Endpoint? {
        if (endpoint == null){
            endpoint = client!!.create(Endpoint::class.java)
        }
        return endpoint
    }

    private fun buildRetrofitClient() {
        val token = MyEkasari.getApp().getToken()
        buildRetrofitClient(token)
    }

    fun buildRetrofitClient(token : String?){
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(2, TimeUnit.MINUTES)
        builder.readTimeout(2, TimeUnit.MINUTES)

        if (DEBUG) {
            var interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            builder.addInterceptor(ChuckInterceptor(MyEkasari.getApp()))
        }

        if (token != null) {
            builder.addInterceptor(getInterceptoreWithHeader("Authorization", "Bearer ${token}"))
        }

        val okHttpClient = builder.build()
        client = Retrofit.Builder()
            .baseUrl(BASE_URL+"api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Helpers.getDefaultGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        endpoint = null

        Log.v("ken", token.toString())
    }

    private fun getInterceptoreWithHeader(headerName : String, headerValue:String) : Interceptor {
        val header = HashMap<String, String>()
        header.put(headerName, headerValue)
        return getInterceptoreWithHeader(header)
    }

    private fun getInterceptoreWithHeader(headers : Map<String, String>) : Interceptor {
        return Interceptor {
            val original = it.request()
            val builder = original.newBuilder()
            for ((key, value) in headers) {
                builder.addHeader(key, value)
            }
            builder.method(original.method, original.body)
            it.proceed(builder.build())
        }
    }





}