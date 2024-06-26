package com.example.paymentapplication.ui.dashboard.profile.activity.security

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.paymentapplication.R
import com.example.paymentapplication.databinding.ActivitySecurityBinding

class SecurityActivity : AppCompatActivity() {

    companion object{
        private val TAG = "Security Activity"
    }

    private lateinit var binding: ActivitySecurityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecurityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.securityToolbar.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Security"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}