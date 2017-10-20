package com.example.gor.simplemarket

import android.app.Application
import com.example.gor.simplemarket.model.Model
import com.example.gor.simplemarket.service.Web
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Gor on 17.10.2017.
 */

class App: Application() {

    companion object {
        const val URL = "https://blackstarshop.ru/"
        val separation = arrayOf("all", "price_up", "price_down", "XS")
        fun getClient(): Web {
            return Retrofit.Builder()
                    .baseUrl(App.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build().create(Web::class.java)
        }
        lateinit var dataList: Array<Model.Category>
    }

}