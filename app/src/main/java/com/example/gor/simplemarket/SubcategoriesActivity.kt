package com.example.gor.simplemarket

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.gor.simplemarket.model.Model
import com.example.gor.simplemarket.presenter.adapters.MyListAdapter


class SubcategoriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subcategories)

        val i = intent.extras.getInt("position")

        val recyclerView = findViewById(R.id.id_recycler_subcategory) as RecyclerView

        if(App.dataList[i].subcategories?.size != 0) {
            findViewById(R.id.id_text_absent).visibility = View.INVISIBLE
            val recyclerAdapter = MyListAdapter<Model.Subcategory>(
                    layoutInflater, App.dataList[i].subcategories?.toTypedArray()!!)
            recyclerView.adapter = recyclerAdapter
            recyclerView.layoutManager = GridLayoutManager(this, 2)
            recyclerView.setHasFixedSize(true)
        }


    }
}
