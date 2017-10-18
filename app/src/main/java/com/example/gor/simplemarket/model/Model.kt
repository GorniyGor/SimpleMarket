package com.example.gor.simplemarket.model

/**
 * Created by Gor on 17.10.2017.
 */
object Model {
    /*data class Result(*//*@SerializedName("68") *//*val categories: Map<String, Category>)*/
    data class Category(val name: String,
                        val sortOrder: Int,
                        val image: String,
                        val iconImage: String?,
                        val iconImageActive: String?,
                        val subcategories: List<Subcategory>?)
    data class Subcategory(val id: String,
                           val iconImage: String,
                           val sortOrder: String,
                           val name: String,
                           val type: String)
    //--------------------------------------------------

    data class Product(val name: String,
                       val mainImage: String,
                       val offers: List<Offer>,
                       val price: Int)
    data class Offer(val size: String)


}