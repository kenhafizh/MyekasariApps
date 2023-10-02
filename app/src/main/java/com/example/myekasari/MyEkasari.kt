package com.example.myekasari

import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.multidex.MultiDexApplication
import com.example.myekasari.network.HttpClient
import com.example.myekasari.utils.PreferencesHelper

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
        PreferencesHelper.setInstance(this)
    }






}