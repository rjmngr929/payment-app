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
import com.example.paymentapplication.adapters.TrxnRecyclerViewAdapter
import com.example.paymentapplication.databinding.FragmentUpiTransferBinding
import com.example.paymentapplication.models.TrxnModel


class UpiTransferFragment : Fragment() {

    companion object{
        private const val TAG = "UPI Transaction Fragment"
    }

    private lateinit var binding: FragmentUpiTransferBinding

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
        binding = FragmentUpiTransferBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//  ************************** RecyclerView UPI Transaction ************************************************

        trxnAryData.add(TrxnModel("Suresh", "xyz@abl","upi"))
        trxnAryData.add(TrxnModel("Rajesh", "abc@upi","upi"))
        trxnAryData.add(TrxnModel("Mukesh", "dss@upi","upi"))
        trxnAryData.add(TrxnModel("Shubham", "sdsdfs@upi","upi"))
        trxnAryData.add(TrxnModel("Sonu", "ssdfds@upi","upi"))
        trxnAryData.add(TrxnModel("Raj", "dsfdsds@upi","upi"))
        trxnAryData.add(TrxnModel("Suresh", "sfsddfs@upi","upi"))
        trxnAryData.add(TrxnModel("Rajesh", "dsfdsfds@upi","upi"))
        trxnAryData.add(TrxnModel("Mukesh", "dsfds@upi","upi"))
        trxnAryData.add(TrxnModel("Shubham", "dsfds@upi","upi"))
        trxnAryData.add(TrxnModel("Sonu", "sfsddfs@upi","upi"))

        val allTrxnRecyclerView = binding.upiTrxnRecyclerview

        allTrxnRecyclerView.layoutManager = LinearLayoutManager(myContext)

        adapter = TrxnRecyclerViewAdapter(myContext, trxnAryData)
        allTrxnRecyclerView.adapter = adapter

        adapter.setItemOnClickListener(object : TrxnRecyclerViewAdapter.TrxnListener {
            override fun onClick() {
                Log.d(TAG, "onClick: Promo activity click")
            }
        })
//  ************************** RecyclerView UPI Transaction ************************************************

    }

}