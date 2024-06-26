package com.example.paymentapplication.ui.dashboard.home.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.paymentapplication.R
import com.example.paymentapplication.adapters.TransferViewPagerAdapter
import com.example.paymentapplication.databinding.ActivityTransferBinding
import com.example.paymentapplication.ui.dashboard.home.fragments.transfer.AllTransferFragment
import com.example.paymentapplication.ui.dashboard.home.fragments.transfer.BankTransferFragment
import com.example.paymentapplication.ui.dashboard.home.fragments.transfer.UpiTransferFragment

class TransferActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "Transfer Activity"
    }

    private lateinit var binding: ActivityTransferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.transferToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Request Payment"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back)

//  ************************** View Pager **********************************************************
        // Initializing the ViewPagerAdapter
        val adapter = TransferViewPagerAdapter(supportFragmentManager)

        // add fragment to the list
        adapter.addFragment(AllTransferFragment(), "All")
        adapter.addFragment(BankTransferFragment(), "Bank")
        adapter.addFragment(UpiTransferFragment(), "UPI")

        // Adding the Adapter to the ViewPager
        binding.transferViewPager.adapter = adapter

        // bind the viewPager with the TabLayout.
        binding.transferTabs.setupWithViewPager(binding.transferViewPager)
//  ************************** View Pager **********************************************************

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