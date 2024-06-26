package com.example.paymentapplication.ui.dashboard.home.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.Toast
import com.example.paymentapplication.R
import com.example.paymentapplication.adapters.ServicesGridViewAdapter
import com.example.paymentapplication.databinding.ActivityServicesListBinding
import com.example.paymentapplication.models.ServicesModel

class ServicesListActivity : AppCompatActivity() {

    companion object{
        private val TAG = "Services List Activity"
    }

    private lateinit var binding: ActivityServicesListBinding

    private lateinit var billServiceAdapter : ServicesGridViewAdapter
    private lateinit var insuranceServiceAdapter : ServicesGridViewAdapter
    private lateinit var optionServiceAdapter : ServicesGridViewAdapter

    private var billServicesList = ArrayList<ServicesModel>()
    private var insuranceServicesList = ArrayList<ServicesModel>()
    private var optionServicesList = ArrayList<ServicesModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServicesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.servicesToolbar.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "All Services"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back)


//  ************************* Bill Section *********************************************************
        billServicesList.add(ServicesModel("Electricity", R.drawable.dummyuser))
        billServicesList.add(ServicesModel("Internet", R.drawable.dummyuser))
        billServicesList.add(ServicesModel("Water", R.drawable.dummyuser))
        billServicesList.add(ServicesModel("E-Wallet", R.drawable.dummyuser))
        billServicesList.add(ServicesModel("Games", R.drawable.dummyuser))
        billServicesList.add(ServicesModel("Television", R.drawable.dummyuser))
        billServicesList.add(ServicesModel("Merchant", R.drawable.dummyuser))
        billServicesList.add(ServicesModel("Installment", R.drawable.dummyuser))
        billServiceAdapter = ServicesGridViewAdapter(billServicesList, this)

        binding.servicesBillGridview.adapter = billServiceAdapter

        binding.servicesBillGridview.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                this, billServicesList[position].name + " selected",
                Toast.LENGTH_SHORT
            ).show()
        }
//  ************************* Bill Section *********************************************************

//  ************************* Insurance Section *********************************************************
        insuranceServicesList.add(ServicesModel("Health", R.drawable.dummyuser))
        insuranceServicesList.add(ServicesModel("Mobile", R.drawable.dummyuser))
        insuranceServicesList.add(ServicesModel("Motor", R.drawable.dummyuser))
        insuranceServicesList.add(ServicesModel("Car", R.drawable.dummyuser))
        insuranceServiceAdapter = ServicesGridViewAdapter(insuranceServicesList, this)

        binding.servicesInsuranceGridview.adapter = insuranceServiceAdapter

        binding.servicesInsuranceGridview.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                this, insuranceServicesList[position].name + " selected",
                Toast.LENGTH_SHORT
            ).show()
        }
//  ************************* Insurance Section *********************************************************

//  ************************* Option Section *********************************************************
        optionServicesList.add(ServicesModel("Assurance", R.drawable.dummyuser))
        optionServicesList.add(ServicesModel("Shopping", R.drawable.dummyuser))
        optionServicesList.add(ServicesModel("Deals", R.drawable.dummyuser))
        optionServicesList.add(ServicesModel("Invest", R.drawable.dummyuser))
        optionServiceAdapter = ServicesGridViewAdapter(optionServicesList, this)

        binding.servicesOptionGridview.adapter = optionServiceAdapter

        binding.servicesOptionGridview.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                this, optionServicesList[position].name + " selected",
                Toast.LENGTH_SHORT
            ).show()
        }
//  ************************* Bill Section *********************************************************

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