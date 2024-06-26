package com.example.paymentapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.paymentapplication.R
import com.example.paymentapplication.models.ServicesModel

class ServicesGridViewAdapter ( private val serviceList: List<ServicesModel>, private val context: Context) : BaseAdapter(){

    private var layoutInflater: LayoutInflater? = null
    private lateinit var courseTV: TextView
    private lateinit var courseIV: ImageView

    override fun getCount(): Int {
        return serviceList.size
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var convertView = p1
        // on blow line we are checking if layout inflater
        // is null, if it is null we are initializing it.
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        // on the below line we are checking if convert view is null.
        // If it is null we are initializing it.
        if (convertView == null) {
            // on below line we are passing the layout file
            // which we have to inflate for each item of grid view.
            convertView = layoutInflater!!.inflate(R.layout.gridview_element_layout, null)
        }
        // on below line we are initializing our course image view
        // and course text view with their ids.
        courseIV = convertView!!.findViewById(R.id.idIVCourse)
        courseTV = convertView!!.findViewById(R.id.idTVCourse)
        // on below line we are setting image for our course image view.
        courseIV.setImageResource(serviceList.get(p0).img)
        // on below line we are setting text in our course text view.
        courseTV.setText(serviceList.get(p0).name)
        // at last we are returning our convert view.
        return convertView
    }

}