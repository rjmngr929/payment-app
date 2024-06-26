package com.example.paymentapplication.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.example.paymentapplication.R
import com.example.paymentapplication.adapters.ServicesGridViewAdapter
import com.example.paymentapplication.databinding.FragmentHomeBinding
import com.example.paymentapplication.models.ServicesModel
import com.example.paymentapplication.ui.accountSetup.AccountSetupActivity
import com.example.paymentapplication.ui.dashboard.home.activity.NotificationActivity
import com.example.paymentapplication.ui.dashboard.home.activity.PromoActivity
import com.example.paymentapplication.ui.dashboard.home.activity.ServicesListActivity
import com.example.paymentapplication.ui.dashboard.home.activity.TransferActivity


class HomeFragment : Fragment() {

    companion object{
        private val TAG = "Home Fragment"
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var myContext: Context

    private lateinit var serviceAdapter : ServicesGridViewAdapter

    private var servicesList = ArrayList<ServicesModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        servicesList.add(ServicesModel("Electricity", R.drawable.dummyuser))
        servicesList.add(ServicesModel("Internet", R.drawable.dummyuser))
        servicesList.add(ServicesModel("Water", R.drawable.dummyuser))
        servicesList.add(ServicesModel("E-Wallet", R.drawable.dummyuser))
        servicesList.add(ServicesModel("Assurance", R.drawable.dummyuser))
        servicesList.add(ServicesModel("Shopping", R.drawable.dummyuser))
        servicesList.add(ServicesModel("Deals", R.drawable.dummyuser))
        servicesList.add(ServicesModel("Health", R.drawable.dummyuser))
        servicesList.add(ServicesModel("Invest", R.drawable.dummyuser))
        servicesList.add(ServicesModel("Merchant", R.drawable.dummyuser))
        servicesList.add(ServicesModel("Mobile", R.drawable.dummyuser))
        servicesList.add(ServicesModel("Games", R.drawable.dummyuser))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Glide.with(this@MainActivity)
//            .load("${it[0].image}")
//            .circleCrop() // Apply circular crop to the image
//            .placeholder(R.drawable.dummyuser) // Placeholder image while loading
//            .error(R.drawable.dummyuser)
//            .transition(DrawableTransitionOptions.withCrossFade()) // Fade transition
//            .into(binding.profilePic)

        serviceAdapter = ServicesGridViewAdapter(servicesList, myContext)

        binding.servicesGridview.adapter = serviceAdapter

        binding.servicesGridview.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                myContext, servicesList[position].name + " selected",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.promoBtn.setOnClickListener {
            startActivity(Intent(myContext, PromoActivity::class.java))
        }

        binding.notificationBtn.setOnClickListener {
            startActivity(Intent(myContext, NotificationActivity::class.java))
        }

        binding.seeAllServicesBtn.setOnClickListener {
            startActivity(Intent(myContext, ServicesListActivity::class.java))
        }

        binding.transferBtn.setOnClickListener {
            startActivity(Intent(myContext, TransferActivity::class.java))
        }

        binding.kycVerifyBtn.setOnClickListener {
            startActivity(Intent(myContext, AccountSetupActivity::class.java))
        }

    }


}