package com.example.paymentapplication.ui.dashboard.home.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paymentapplication.R
import com.example.paymentapplication.adapters.PromoRecyclerViewAdapter
import com.example.paymentapplication.databinding.ActivityPromoBinding
import com.example.paymentapplication.models.PromoModel

class PromoActivity : AppCompatActivity() {

    companion object{
        private val TAG = "Promo Activity"
    }

    private lateinit var binding: ActivityPromoBinding

    private lateinit var adapter : PromoRecyclerViewAdapter

    private var promoAryData = ArrayList<PromoModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPromoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.promoToolbar.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Promo & Discount"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back)

//  ************************** RecyclerView Promo ************************************************

        promoAryData.add(PromoModel())
        promoAryData.add(PromoModel())
        promoAryData.add(PromoModel())
        promoAryData.add(PromoModel())
        promoAryData.add(PromoModel())
        promoAryData.add(PromoModel())
        promoAryData.add(PromoModel())

        val promoRecyclerView = binding.promoRecyclerview

        promoRecyclerView.layoutManager = LinearLayoutManager(this)

        adapter = PromoRecyclerViewAdapter(this,promoAryData)
        promoRecyclerView.adapter = adapter

        adapter.setItemOnClickListener(object : PromoRecyclerViewAdapter.PromoListener {
            override fun onClick() {
                Log.d(TAG, "onClick: Promo activity click")
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