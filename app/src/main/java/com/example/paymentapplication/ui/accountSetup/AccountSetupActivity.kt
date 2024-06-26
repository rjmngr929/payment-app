package com.example.paymentapplication.ui.accountSetup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.paymentapplication.R
import com.example.paymentapplication.databinding.ActivityAccountSetupBinding
import com.example.paymentapplication.ui.accountSetup.fragment.ReasonForAppFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountSetupActivity : AppCompatActivity() {

    companion object{
        private val TAG = "Account Setup Activity"
    }

    private lateinit var binding: ActivityAccountSetupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountSetupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.accountSetupToolbar.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Kyc Verify"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back)



    }


//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home -> {
////                if(getCurrentFragment() !is ReasonForAppFragment){
////                    val navController = findNavController(R.id.myNavHostAccountSetupFragment)
////                    navController.popBackStack()
////                }
//                finish()
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

//    private fun getCurrentFragment(): Fragment? {
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostAccountSetupFragment) as NavHostFragment?
//        return navHostFragment?.childFragmentManager?.fragments?.get(0)
//    }

}