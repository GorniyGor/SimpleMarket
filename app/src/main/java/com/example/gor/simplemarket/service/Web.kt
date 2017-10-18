package com.example.gor.simplemarket.service

import com.example.gor.simplemarket.model.Model
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Gor on 17.10.2017.
 */

interface Web {

    @GET("index.php?route=api/v1/categories")
    fun getCategoriesList(): Observable<Map<String, Model.Category>>

    @GET("index.php?route=api/v1/products")
    fun getSubcategoriesList(@Query("cat_id") id: String): Observable<Map<String,Model.Product>>
}