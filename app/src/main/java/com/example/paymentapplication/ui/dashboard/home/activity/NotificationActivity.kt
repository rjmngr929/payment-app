package com.example.paymentapplication.ui.dashboard.home.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paymentapplication.R
import com.example.paymentapplication.adapters.NotificationRecyclerviewAdapter
import com.example.paymentapplication.adapters.PromoRecyclerViewAdapter
import com.example.paymentapplication.databinding.ActivityNotificationBinding
import com.example.paymentapplication.models.NotificationModel
import com.example.paymentapplication.models.PromoModel

class NotificationActivity : AppCompatActivity() {

    companion object{
        private val TAG = "Notification Activity"
    }

    private lateinit var binding: ActivityNotificationBinding


    private lateinit var adapter : NotificationRecyclerviewAdapter

    private var notificationAryData = ArrayList<NotificationModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.notificationToolbar.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Notifications"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back)

//  ************************** RecyclerView Promo ************************************************

        notificationAryData.add(NotificationModel("","You Get Cashback!","You get $5 cashback from payment"))
        notificationAryData.add(NotificationModel("","New Service is Available!","Now you can do payment tracking"))
        notificationAryData.add(NotificationModel("","Netflix Subscription Bill","Subscription bill paid to Netflix"))
        notificationAryData.add(NotificationModel("","E-Wallet Is Connected","Your E-Wallet is connected to app"))
        notificationAryData.add(NotificationModel("","Verification Successful","Account verification complete"))
        notificationAryData.add(NotificationModel("","New Service is Available!","Now you can do payment tracking"))
        notificationAryData.add(NotificationModel("","E-Wallet Is Connected","Your E-Wallet is connected to app"))

        val notifyRecyclerView = binding.notificationRecyclerview

        notifyRecyclerView.layoutManager = LinearLayoutManager(this)

        adapter = NotificationRecyclerviewAdapter(this,notificationAryData)
        notifyRecyclerView.adapter = adapter

        adapter.setItemOnClickListener(object : NotificationRecyclerviewAdapter.NotifyListener {
            override fun onClick() {
                Log.d(TAG, "onClick: Notification activity click")
            }
        })
//  ************************** RecyclerView Promo ************************************************

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