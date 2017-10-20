package com.example.gor.simplemarket.model.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.gor.simplemarket.R
import com.example.gor.simplemarket.model.Model
import com.example.gor.simplemarket.model.holders.MyHolder

/**
 * Created by Gor on 18.10.2017.
 */

class MyListAdapter<T>(private val inflater: LayoutInflater,
                       private val collection: Array<T>): RecyclerView.Adapter<MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyHolder =
            MyHolder(inflater.inflate(R.layout.item_layout, parent, false),
                    collection.javaClass)

    override fun onBindViewHolder(holder: MyHolder?, i: Int) {
        when(collection.javaClass) {
            Array<Model.Category>::class.java ->{
                collection as Array<Model.Category>
                holder?.setParameters(collection[i].image, collection[i].name, i = i)
            }
            Array<Model.Subcategory>::class.java ->{
                collection as Array<Model.Subcategory>
                holder?.setParameters(collection[i].iconImage, collection[i].name,
                        i = collection[i].id.toInt())
            }
            Array<Model.Product>::class.java ->{
                collection as Array<Model.Product>
                holder?.setParameters(collection[i].mainImage,
                        collection[i].name, collection[i].price,
                        collection[i].offers.flatMap{listOf(it.size)})
            }

        }
    }

    override fun getItemCount(): Int = collection.size
}
