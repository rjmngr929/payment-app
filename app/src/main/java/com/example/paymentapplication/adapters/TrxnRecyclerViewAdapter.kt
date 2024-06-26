package com.example.paymentapplication.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.paymentapplication.R
import com.example.paymentapplication.models.TrxnModel
import kotlin.random.Random

class TrxnRecyclerViewAdapter(private val context: Context, private val mList: ArrayList<TrxnModel>) : RecyclerView.Adapter<TrxnRecyclerViewAdapter.ViewHolder>() {

    private lateinit var listener : TrxnListener

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.all_trxn_layout, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]


        if(mList.isNotEmpty()){

            holder.Name.text = ItemsViewModel.userName

            if(ItemsViewModel.trxnType == "upi"){
                holder.AccNumber.text = String.format("upi: %s", ItemsViewModel.trxnNumber)
            }else{
                holder.AccNumber.text = String.format("Account: %s", ItemsViewModel.trxnNumber)
            }


            holder.clickCard.setOnClickListener {
                this.listener.onClick()
            }

        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    fun setItemOnClickListener(listener: TrxnListener) {
        this.listener = listener
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val clickCard = ItemView.rootView
        val Name: TextView = itemView.findViewById(R.id.trxn_username_text)
        val AccNumber: TextView = itemView.findViewById(R.id.trxn_number_text)
//        val profileText: TextView = itemView.findViewById(R.id.profile_text)

    }

    interface  TrxnListener{
        fun onClick()
    }
}