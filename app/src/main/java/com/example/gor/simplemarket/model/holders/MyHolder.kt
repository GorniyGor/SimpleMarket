package com.example.gor.simplemarket.model.holders

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.gor.simplemarket.App
import com.example.gor.simplemarket.ProductsActivity
import com.example.gor.simplemarket.R
import com.example.gor.simplemarket.SubcategoriesActivity
import com.example.gor.simplemarket.model.Model
import com.example.gor.simplemarket.presenter.adapters.MyListAdapter
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by Gor on 18.10.2017.
 */
class MyHolder(itemView: View, val type: Any) : RecyclerView.ViewHolder(itemView) {

    private val image = itemView.findViewById<ImageView>(R.id.id_image)
    private val name = itemView.findViewById<TextView>(R.id.id_textName)
    private val price = itemView.findViewById<TextView>(R.id.id_textPrice)
    private val size = itemView.findViewById<TextView>(R.id.id_textSize)

    private var provide: Single<MyListAdapter.Parameters>.() -> Unit = {subscribeBy(
            onSuccess = {
                Picasso.with(itemView.context).load(App.URL + it.bitmap)
                        .resize(100, 100).centerCrop().into(image)
                name.text = it.stringName
                price.text = it.stringPrice
                size.text = it.stringSize?.fold("",{
                    sequence, element -> sequence.plus("$element ")
                }) //-Выводим все имеющиеся размеры вещи в строчку

                Observable.just(it.i).subscribe { pos ->
                    itemView.setOnClickListener {
                        val intent: Intent
                        when (type) {
                            Array<Model.Category>::class.java -> {
                                intent = Intent(itemView.context, SubcategoriesActivity::class.java)
                                intent.putExtra("position", pos)
                                itemView.context.startActivity(intent)
                            }

                            Array<Model.Subcategory>::class.java -> {
                                intent = Intent(itemView.context, ProductsActivity::class.java)
                                intent.putExtra("position", pos)
                                itemView.context.startActivity(intent)
                            }

                        }
                    }
                }
            },
            onError = {
                Toast.makeText(itemView.context, "ErrorHolder", Toast.LENGTH_SHORT).show()
                it.printStackTrace()}
    )}

    fun updateData(provider: Single<MyListAdapter.Parameters>) {
        provider.provide()
    }


}