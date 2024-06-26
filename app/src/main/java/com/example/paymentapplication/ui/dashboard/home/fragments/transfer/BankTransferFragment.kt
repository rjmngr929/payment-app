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
import com.example.paymentapplication.databinding.FragmentBankTransferBinding
import com.example.paymentapplication.models.TrxnModel


class BankTransferFragment : Fragment() {

    companion object{
        private const val TAG = "Bank Transfer Fragment"
    }

    private lateinit var binding: FragmentBankTransferBinding

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
        binding = FragmentBankTransferBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    //  ************************** RecyclerView Bank Transaction ************************************************

        trxnAryData.add(TrxnModel("Suresh", "45123698745","bank"))
        trxnAryData.add(TrxnModel("Rajesh", "5215555555544","bank"))
        trxnAryData.add(TrxnModel("Mukesh", "155555151515","bank"))
        trxnAryData.add(TrxnModel("Shubham", "15555555555","bank"))
        trxnAryData.add(TrxnModel("Sonu", "556555115155","bank"))
        trxnAryData.add(TrxnModel("Raj", "555515551515547","bank"))
        trxnAryData.add(TrxnModel("Suresh", "45123698745","bank"))
        trxnAryData.add(TrxnModel("Rajesh", "5215555555544","bank"))
        trxnAryData.add(TrxnModel("Mukesh", "155555151515","bank"))
        trxnAryData.add(TrxnModel("Shubham", "15555555555","bank"))
        trxnAryData.add(TrxnModel("Sonu", "556555115155","bank"))

        val allTrxnRecyclerView = binding.bankTrxnRecyclerview

        allTrxnRecyclerView.layoutManager = LinearLayoutManager(myContext)

        adapter = TrxnRecyclerViewAdapter(myContext, trxnAryData)
        allTrxnRecyclerView.adapter = adapter

        adapter.setItemOnClickListener(object : TrxnRecyclerViewAdapter.TrxnListener {
            override fun onClick() {
                Log.d(TAG, "onClick: Promo activity click")
            }
        })
//  ************************** RecyclerView Bank Transaction ************************************************

    }

}