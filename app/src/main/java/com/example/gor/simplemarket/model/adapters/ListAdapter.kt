package com.example.gor.simplemarket.model.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.gor.simplemarket.R
import com.example.gor.simplemarket.model.holders.MyHolder

/**
 * Created by Gor on 18.10.2017.
 */

class ListAdapter<T>(private val inflater: LayoutInflater,
                            private val array: Array<T>): RecyclerView.Adapter<MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyHolder =
            MyHolder(inflater.inflate(R.layout.item_layout, parent, false))

    override fun onBindViewHolder(holder: MyHolder?, position: Int) {
        TODO("Observable со списком данных, которые нужно установать в holder," +
                "а в holder соответсвенно Subscriber")

    }

    override fun getItemCount(): Int = array.size
}
