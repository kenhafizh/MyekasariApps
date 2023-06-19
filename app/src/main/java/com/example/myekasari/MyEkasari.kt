package com.example.myekasari

import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.multidex.MultiDexApplication
import com.example.myekasari.network.HttpClient

@Suppress("DEPRECATION")
class MyEkasari : MultiDexApplication() {

    companion object {
        lateinit var instance : MyEkasari

        fun getApp() : MyEkasari {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getPreferences() : SharedPreferences{
        return PreferenceManager.getDefaultSharedPreferences((this))

    }

    fun setToken(token:String) {
        getPreferences().edit().putString("PREFERENCES_TOKEN", token).apply ()
        HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun getToken(): String? {
        return getPreferences().getString("PREFERENCES_TOKEN", null)
    }

    fun setUser(token:String) {
        getPreferences().edit().putString("PREFERENCES_USER", token).apply ()
        HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun setToken(): String? {
        return getPreferences().getString("PREFERENCES_USER", null)
    }



}