package com.example.gor.simplemarket

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.example.gor.simplemarket.model.Model
import com.example.gor.simplemarket.model.adapters.MyListAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ProductsActivity : AppCompatActivity() {

    lateinit var spinner: Spinner
    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView
    private val separation = arrayOf("all", "price_up", "price_down", "XS")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        progressBar = findViewById(R.id.id_progressBar_main) as ProgressBar
        progressBar.visibility = ProgressBar.VISIBLE

        spinner = findViewById(R.id.id_spinner) as Spinner
        spinner.adapter = ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, separation)



        //----------------------------------------------------------------------

        val id = intent.extras.getInt("position")

        recyclerView = findViewById(R.id.id_recycler_product) as RecyclerView

        val client = App.getClient()

        val request = client.getProductList(id.toString())
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
                            spinnerWorker(it.values.toTypedArray())
                        },
                        onError =  {
                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                            it.printStackTrace()
                        })


    }

    private fun spinnerWorker(array: Array<Model.Product>){
        Observable.create<Array<Model.Product>> {
            spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener  {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val arrayClone = array.clone()
                    it.onNext(
                            when(p2){
                        0 -> array
                        1 -> arrayClone.apply{ arrayClone.sortBy { it.price.toDouble() }}
                        2 -> arrayClone.apply{arrayClone.sortByDescending { it.price.toDouble() }}
                        3 -> array.filter {
                            it.offers.flatMap{listOf(it.size)}.contains(separation[3])
                        }.toTypedArray()
                        else -> array
                    })
                }
            }
        }.subscribeBy (
           onNext = {
               recyclerView.adapter = MyListAdapter<Model.Product>(layoutInflater, it)
               recyclerView.adapter.notifyDataSetChanged()},
           onError = {
               Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
               it.printStackTrace()}
        )
    }
}
