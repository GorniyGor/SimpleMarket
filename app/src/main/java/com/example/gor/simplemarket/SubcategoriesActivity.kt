package com.example.gor.simplemarket

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.gor.simplemarket.model.Model
import com.example.gor.simplemarket.model.adapters.MyListAdapter


class SubcategoriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val i = intent.extras.getInt("position")

        val recyclerView = RecyclerView(this)

        val recyclerAdapter = MyListAdapter<Model.Subcategory>(
                layoutInflater, App.dataList[i].subcategories?.toTypedArray()!!)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.setHasFixedSize(true)
        setContentView(recyclerView)


    }
}
