package com.example.paymentapplication.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.paymentapplication.ui.Auth.login.LoginActivity
import com.example.paymentapplication.ui.createNewPin.CreateNewPinActivity
import com.example.paymentapplication.ui.dashboard.MainActivity
import com.example.paymentapplication.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    companion object{
        private val TAG = "Splash Screen Activity"
    }

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)

        if(tokenManager.getToken() != null){
            startActivity(Intent(this, MainActivity::class.java))
        }else{
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()

    }
}