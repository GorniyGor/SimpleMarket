package com.example.gor.simplemarket.service

import com.example.gor.simplemarket.model.Model
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Gor on 17.10.2017.
 */

interface Web {

    @GET("index.php?route=api/v1/categories")
    fun getCategoriesList(): Single<Map<String, Model.Category>>

    @GET("index.php?route=api/v1/products")
    fun getProductList(@Query("cat_id") id: String): Single<Map<String,Model.Product>>

}