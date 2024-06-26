package com.example.paymentapplication.ui.Auth.forgot

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.paymentapplication.R
import com.example.paymentapplication.databinding.ActivityForgotPasswordBinding
import com.example.paymentapplication.ui.Auth.forgot.fragment.SelectContactVerifyFragment


class ForgotPasswordActivity : AppCompatActivity() {

    companion object{
        private val TAG = "Forgot Password Activity"
    }

    private lateinit var binding: ActivityForgotPasswordBinding

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.forgotPassToolbar.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Forgot Password"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if(getCurrentFragment() is SelectContactVerifyFragment){
                    finish()
                }else{
                    val navController = findNavController(R.id.myNavHostForgotFragment)
                    navController.navigate(R.id.action_forgotOtpVerifyFragment_to_selectContactVerifyFragment)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getCurrentFragment(): Fragment? {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostForgotFragment) as NavHostFragment?
        return navHostFragment?.childFragmentManager?.fragments?.get(0)
    }

}