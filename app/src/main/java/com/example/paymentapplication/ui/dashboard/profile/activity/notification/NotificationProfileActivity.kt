package com.example.paymentapplication.ui.dashboard.profile.activity.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.paymentapplication.R
import com.example.paymentapplication.databinding.ActivityNotificationProfileBinding

class NotificationProfileActivity : AppCompatActivity() {

    companion object{
        private val TAG = "Notification Profile Activity"
    }

    private lateinit var binding: ActivityNotificationProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.profileNotificationToolbar.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Notification"
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