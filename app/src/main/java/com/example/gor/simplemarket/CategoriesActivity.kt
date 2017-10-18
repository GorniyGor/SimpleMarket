package com.example.gor.simplemarket

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.gor.simplemarket.service.Web
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CategoriesActivity : AppCompatActivity() {

//    val recyclerView = RecyclerView(this)
    /*val recyclerAdapder = ProductListAdapter()*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //--------------------------------------------------------------------------------------

        val client = getClient()

        val request = client.getCategoriesList()
        request.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            Toast.makeText(this, it.values.toTypedArray()[0].name, Toast.LENGTH_SHORT).show()

                        },
                        onError =  { Toast.makeText(this, /*it.categories.name*/
                                "Error", Toast.LENGTH_SHORT).show()
                            it.printStackTrace()},
                        onComplete = { Toast.makeText(this, /*it.categories.name*/
                                "Done!", Toast.LENGTH_SHORT).show()})

    }



    fun getClient(): Web{
        return Retrofit.Builder()
                .baseUrl(App.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(Web::class.java)
    }

    /*client.getSubcategoriesList(
    it.values.toTypedArray().filter
    { it.name == "Мужская" }[0].subcategories?.get(2)?.id!!
    ).observeOn(AndroidSchedulers.mainThread())
    .subscribeOn(Schedulers.io())
    .subscribeBy(
    onNext = {Toast.makeText(this, *//*"Success"*//*
            it.values.toTypedArray()[0].name,
            Toast.LENGTH_SHORT).show()}
    )*/
}
