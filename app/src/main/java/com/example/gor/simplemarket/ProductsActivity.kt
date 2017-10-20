package com.example.gor.simplemarket

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import com.example.gor.simplemarket.model.Model
import com.example.gor.simplemarket.model.adapters.MyListAdapter
import com.example.gor.simplemarket.presenter.ProductPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ProductsActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val progressBar = findViewById(R.id.id_progressBar_main) as ProgressBar
        progressBar.visibility = ProgressBar.VISIBLE

        spinner = findViewById(R.id.id_spinner) as Spinner
        spinner.adapter = ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, App.separation)

        //----------------------------------------------------------------------

        recyclerView = findViewById(R.id.id_recycler_product) as RecyclerView

        val client = App.getClient()

        val catId = intent.extras.getInt("position")
        val request = client.getProductList(catId.toString())
        request.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSuccess { progressBar.visibility = ProgressBar.INVISIBLE }
                .subscribeBy(
                        onSuccess = {
                            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                            recyclerView.adapter = MyListAdapter<Model.Product>(
                                    layoutInflater, it.values.toTypedArray())
                            recyclerView.layoutManager = GridLayoutManager(this, 2)
                            recyclerView.setHasFixedSize(true)
                            toSort(it.values.toTypedArray())
                        },
                        onError =  {
                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                            it.printStackTrace()
                        })


    }

    private fun toSort(array: Array<Model.Product>){
        val observable = ProductPresenter(spinner, array).obs
        observable.connect()
        observable.subscribeBy (
                onNext = {
                    recyclerView.adapter = MyListAdapter<Model.Product>(layoutInflater, it)
                    recyclerView.adapter.notifyDataSetChanged()},
                onError = {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    it.printStackTrace()}
        )
    }
}
