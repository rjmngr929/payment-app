package com.example.paymentapplication.ui.Auth.login

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.paymentapplication.R
import com.example.paymentapplication.databinding.ActivityLoginBinding
import com.example.paymentapplication.ui.Auth.login.fragments.EnterMobileFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "Login Activity"
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Log.d(TAG, "onOptionsItemSelected: get currentFragment => ${getCurrentFragment()}")
                if(getCurrentFragment() is EnterMobileFragment){
                    finish()
                }else{
                    val navController = findNavController(R.id.myNavHost_loginFragment)
                    navController.navigate(R.id.action_otpVerifyFragment_to_enterMobileFragment)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun getCurrentFragment(): Fragment? {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHost_loginFragment) as NavHostFragment?
        return navHostFragment?.childFragmentManager?.fragments?.get(0)
    }

}

