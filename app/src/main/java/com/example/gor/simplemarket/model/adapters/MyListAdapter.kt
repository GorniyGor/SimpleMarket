package com.example.gor.simplemarket.model.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.gor.simplemarket.R
import com.example.gor.simplemarket.model.Model
import com.example.gor.simplemarket.model.holders.MyHolder
import io.reactivex.Single

/**
 * Created by Gor on 18.10.2017.
 */

class MyListAdapter<T>(private val inflater: LayoutInflater,
                       private val collection: Array<T>): RecyclerView.Adapter<MyHolder>() {

    lateinit var parametersProvider: Single<Parameters>

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyHolder =
            MyHolder(inflater.inflate(R.layout.item_layout, parent, false),
                    collection.javaClass)

    override fun onBindViewHolder(holder: MyHolder?, i: Int) {
        parametersProvider = Single.create<Parameters> {
            when(collection.javaClass) {
                Array<Model.Category>::class.java -> {
                    collection as Array<Model.Category>
                    it.onSuccess(Parameters(collection[i].image, collection[i].name, i = i))
                }
                Array<Model.Subcategory>::class.java -> {
                    collection as Array<Model.Subcategory>
                    it.onSuccess(Parameters(collection[i].iconImage, collection[i].name,
                            i = collection[i].id.toInt()))
                }
                Array<Model.Product>::class.java -> {
                    collection as Array<Model.Product>
                    it.onSuccess(Parameters(collection[i].mainImage,
                            collection[i].name, collection[i].price,
                            collection[i].offers.flatMap { listOf(it.size) }))
                }
            }
        }
        holder?.updateData(parametersProvider)
    }

    override fun getItemCount(): Int = collection.size

    data class Parameters(var bitmap: String,
                          var stringName: String,
                          var stringPrice: String = "",
                          var stringSize: List<String>? = null,
                          var i: Int = 0)
}
