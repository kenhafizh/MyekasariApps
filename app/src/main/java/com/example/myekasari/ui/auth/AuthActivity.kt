package com.example.myekasari.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.widget.Toolbar
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.myekasari.R
import com.example.myekasari.databinding.ActivityAuthBinding
import com.example.myekasari.databinding.LayoutToolbarBinding
import com.example.myekasari.ui.auth.signup.SignupFragment
import java.util.zip.Inflater

class   AuthActivity : AppCompatActivity() {

lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val pageRequest = intent.getIntExtra(EXTRA_PAGE_REQUEST,0)
        if(pageRequest == EXTRA_PAGE_SIGN_UP) {
            toolbarSignUp()
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.fragmentSignIn,true)
                .build()

            Navigation.findNavController(findViewById(R.id.authHostFragment))
                .navigate(R.id.action_signup,null, navOptions)
        }

        val pageRequestt = intent.getIntExtra(EXTRA_PAGE_REQUEST,0)
        if (pageRequestt == EXTRA_PAGE_SIGN_UP_ADDRESS) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.fragmentSignUpAddress, true)
                .build()

            Navigation.findNavController(findViewById(R.id.authHostFragment))
                .navigate(R.id.action_signup_address, null, navOptions)
        }


    }

    fun toolbarSignUp(){
        binding.toolbar.toolbar2.title = "Sign Up"
        binding.toolbar.toolbar2.subtitle = "Daftar dong kids"
        binding.toolbar.toolbar2.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000, null)
        binding.toolbar.toolbar2.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarSignUpAddress(){
        binding.toolbar.toolbar2.title = "Address"
        binding.toolbar.toolbar2.subtitle = "Isi yang benar plz"
        binding.toolbar.toolbar2.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000, null)
        binding.toolbar.toolbar2.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarSignUpSuccess(){
        binding.toolbar.toolbar2.visibility = View.GONE
    }




   /* fun toolbarSignUp() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Sign Up"
        toolbar.subtitle = "Register and Eat"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000, null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarSignUpAddress() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Address"
        toolbar.subtitle = "Make sure it's valid"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000, null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarSignUpSuccess(){
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.visibility= View.GONE
   }
*/
    companion object {
        const val EXTRA_PAGE_SIGN_IN = 1
        const val EXTRA_PAGE_SIGN_UP = 2
        const val EXTRA_PAGE_SIGN_UP_ADDRESS = 3
        const val EXTRA_PAGE_REQUEST = "extra_page_request"
    }


}
