package com.example.gor.simplemarket.model.holders

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.gor.simplemarket.App
import com.example.gor.simplemarket.ProductsActivity
import com.example.gor.simplemarket.R
import com.example.gor.simplemarket.SubcategoriesActivity
import com.example.gor.simplemarket.model.Model
import com.squareup.picasso.Picasso

/**
 * Created by Gor on 18.10.2017.
 */
class MyHolder(itemView: View, val type: Any) : RecyclerView.ViewHolder(itemView) {

    /*val selectedObservable = Observable.create<()->Unit> {
        emitter -> itemView.setOnClickListener {
            emitter.onNext{
                val intent = Intent(itemView.context, SubcategoriesActivity::class.java)
                intent.putExtra("position", number)
                itemView.context.startActivity(intent)
            }
        }
    }*/

    val image = itemView.findViewById<ImageView>(R.id.id_image)
    val name = itemView.findViewById<TextView>(R.id.id_textName)
    val price = itemView.findViewById<TextView>(R.id.id_textPrice)
    val size = itemView.findViewById<TextView>(R.id.id_textSize)

    fun setParameters(bitmap: String, stringName: String,
                      stringPrice: String = "", stringSize: List<String>? = null, i: Int = 0){

        Picasso.with(itemView.context)
                .load(App.URL + bitmap)
                .resize(100, 100)
                .centerCrop()
                .into(image)
        name.text = stringName
        price.text = stringPrice
        size.text = stringSize?.fold("",{
            sequence, element -> sequence.plus("$element ")
        })

        itemView.setOnClickListener {
            var intent: Intent
            when(type){
                Array<Model.Category>::class.java -> {
                    intent = Intent(itemView.context, SubcategoriesActivity::class.java)
                    intent.putExtra("position", i)
                    itemView.context.startActivity(intent)
                }

                Array<Model.Subcategory>::class.java -> {
                    intent = Intent(itemView.context, ProductsActivity::class.java)
                    intent.putExtra("position", i)
                    itemView.context.startActivity(intent)
                }

            }
        }


    }




}