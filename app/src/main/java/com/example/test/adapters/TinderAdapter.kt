package com.example.test.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.test.R
import com.example.test.models.Tinder


class TinderAdapter(dataSet: ArrayList<Tinder>, context: Context) :
    BaseAdapter() {

    private val dataSet: ArrayList<Tinder>
    private val context: Context

    override fun getCount(): Int {
        // in get count method we are returning the size of our array list.
        return dataSet.size
    }

    override fun getItem(position: Int): Any {
        // in get item method we are returning the item from our array list.
        return dataSet[position]
    }

    override fun getItemId(position: Int): Long {
        // in get item id we are returning the position.
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = convertView

        if (v == null) {
            v = LayoutInflater.from(parent.context).inflate(R.layout.tinder_item, parent, false)
        }

        (v?.findViewById<View>(R.id.qtText) as TextView).text = dataSet[position].activity




        return v
    }

    init {
        this.dataSet = dataSet
        this.context = context
    }

}
