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
import com.example.paymentapplication.models.NotificationModel
import com.example.paymentapplication.models.PromoModel
import kotlin.random.Random

class NotificationRecyclerviewAdapter(private val context: Context, private val mList: ArrayList<NotificationModel>) : RecyclerView.Adapter<NotificationRecyclerviewAdapter.ViewHolder>() {

    private lateinit var listener : NotifyListener

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notify_recycler_layout, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]


        if(mList.isNotEmpty()){

            holder.cardTitle.text = ItemsViewModel.title
            holder.subTitle.text = ItemsViewModel.subTitle

            holder.promoCard.setOnClickListener {
                this.listener.onClick()
            }

        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    fun setItemOnClickListener(listener: NotifyListener) {
        this.listener = listener
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val promoCard = ItemView.rootView
        val cardLayout: CardView = itemView.findViewById(R.id.notify_card_layout)
        val cardTitle: TextView = itemView.findViewById(R.id.notify_title)
        val subTitle: TextView = itemView.findViewById(R.id.notify_desc)

    }

    private fun getRandomColorFromArray(): Int {
        val colors = arrayOf(
            R.color.promo_blue,
            R.color.promo_purple,
            R.color.promo_green,
            R.color.promo_red
        )
        val randomIndex = Random.nextInt(colors.size)
        return colors[randomIndex]
    }

    interface  NotifyListener{
        fun onClick()
    }
}