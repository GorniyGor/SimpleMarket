package com.example.gor.simplemarket

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import android.widget.Toast
import com.example.gor.simplemarket.model.Model
import com.example.gor.simplemarket.model.adapters.MyListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CategoriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        val progressBar = findViewById(R.id.id_progressBar_main) as ProgressBar
        progressBar.visibility = ProgressBar.VISIBLE

        val recyclerView = findViewById(R.id.id_recycler_category) as RecyclerView

        //--------------------------------------------------------------------------------------

        val client = App.getClient()

        val request = client.getCategoriesList()
        request.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSuccess { progressBar.visibility = ProgressBar.INVISIBLE }
                .subscribeBy(
                        onSuccess = {
                            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                            App.dataList = it.values.toTypedArray()
                            val recyclerAdapter = MyListAdapter<Model.Category>(
                                    layoutInflater, it.values.toTypedArray())
                            recyclerView.adapter = recyclerAdapter
                            recyclerView.layoutManager = GridLayoutManager(this, 2)
                            recyclerView.setHasFixedSize(true)
                        },
                        onError =  { Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                            it.printStackTrace()})

    }
}
