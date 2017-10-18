package com.example.gor.simplemarket.model.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.gor.simplemarket.R
import com.squareup.picasso.Picasso

/**
 * Created by Gor on 18.10.2017.
 */
class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val image = itemView.findViewById<ImageView>(R.id.id_image)
    fun setImage(imageUrl: String) {
        Picasso.with(itemView.context).load(imageUrl).into(image)
    }

    val name = itemView.findViewById<TextView>(R.id.id_textName)
    fun setName(stringName: String) {
        name.text = stringName
    }

    val price = itemView.findViewById<TextView>(R.id.id_textPrice)
    fun setPrice(stringPrice: String) {
        price.text = stringPrice
    }

    val size = itemView.findViewById<TextView>(R.id.id_textSize)
    fun setSiz(stringSize: String) {
        size.text = stringSize
    }


}