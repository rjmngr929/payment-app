package com.example.paymentapplication.ui.dashboard.home.fragments.transfer

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paymentapplication.R
import com.example.paymentapplication.adapters.PromoRecyclerViewAdapter
import com.example.paymentapplication.adapters.TrxnRecyclerViewAdapter
import com.example.paymentapplication.databinding.FragmentAllTransferBinding
import com.example.paymentapplication.models.PromoModel
import com.example.paymentapplication.models.TrxnModel
import com.example.paymentapplication.ui.dashboard.home.activity.PromoActivity


class AllTransferFragment : Fragment() {

    companion object{
        private const val TAG = "All Transaction Fragment"
    }

    private lateinit var binding: FragmentAllTransferBinding

    private lateinit var myContext: Context

    private lateinit var adapter : TrxnRecyclerViewAdapter

    private var trxnAryData = ArrayList<TrxnModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllTransferBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//  ************************** RecyclerView All Transaction ************************************************

        trxnAryData.add(TrxnModel("Suresh", "45123698745", "bank"))
        trxnAryData.add(TrxnModel("Rajesh", "5215555555544", "bank"))
        trxnAryData.add(TrxnModel("Mukesh", "abc@ybl", "upi"))
        trxnAryData.add(TrxnModel("Shubham", "hdahd@api", "upi"))
        trxnAryData.add(TrxnModel("Sonu", "dhd@upi", "upi"))
        trxnAryData.add(TrxnModel("Raj", "555515551515547", "bank"))
        trxnAryData.add(TrxnModel("Suresh", "dsfs@upi", "upi"))
        trxnAryData.add(TrxnModel("Rajesh", "5215555555544", "bank"))
        trxnAryData.add(TrxnModel("Mukesh", "dsabc@upi", "upi"))
        trxnAryData.add(TrxnModel("Shubham", "fdsabc@upi", "upi"))
        trxnAryData.add(TrxnModel("Sonu", "dssdabc@upi", "upi"))

        val allTrxnRecyclerView = binding.allTrxnRecyclerview

        allTrxnRecyclerView.layoutManager = LinearLayoutManager(myContext)

        adapter = TrxnRecyclerViewAdapter(myContext, trxnAryData)
        allTrxnRecyclerView.adapter = adapter

        adapter.setItemOnClickListener(object : TrxnRecyclerViewAdapter.TrxnListener {
            override fun onClick() {
                Log.d(TAG, "onClick: Promo activity click")
            }
        })
//  ************************** RecyclerView All Transaction ************************************************

    }

}