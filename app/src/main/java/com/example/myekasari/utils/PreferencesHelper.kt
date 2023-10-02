package com.example.myekasari.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.myekasari.network.HttpClient


object PreferencesHelper {
        private var sharedPrefs: SharedPreferences? = null

        fun setInstance(context: Context) {
            sharedPrefs =
                context.getSharedPreferences("myekasari_shared_prefs", Context.MODE_PRIVATE)
        }

        fun clear() = sharedPrefs?.edit()?.clear()?.commit()


        fun setToken(token:String) =
            sharedPrefs?.edit()?.putString("PREFERENCES_TOKEN", token)?.apply()


        fun getToken(): String? =
            sharedPrefs?.getString("PREFERENCES_TOKEN", null)


        fun setUser(user:String) =
            sharedPrefs?.edit()?.putString("PREFERENCES_USER", user)?.apply ()


        fun getUser(): String? =
            sharedPrefs?.getString("PREFERENCES_USER", null)

}