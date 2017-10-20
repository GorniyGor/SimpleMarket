package com.example.gor.simplemarket.presenter

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import com.example.gor.simplemarket.App
import com.example.gor.simplemarket.model.Model
import io.reactivex.Observable
import io.reactivex.observables.ConnectableObservable

/**
 * Created by Gor on 20.10.2017.
 */
class ProductPresenter(view: View, data: Any) {

    private val spinner = view as Spinner
    val array = data as Array<Model.Product>

    val obs: ConnectableObservable<Array<Model.Product>> =
            Observable.create<Array<Model.Product>> {
                spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener  {
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val arrayClone = array.clone()
                        it.onNext(
                                when(p2){
                                    0 -> array
                                    1 -> arrayClone.apply{ arrayClone.sortBy {it.price.toDouble()}}
                                    2 -> arrayClone.apply{ arrayClone.sortByDescending {it.price.toDouble()}}
                                    3 -> array.filter {
                                        it.offers.flatMap{listOf(it.size)}.contains(App.separation[3])
                                    }.toTypedArray()
                                    else -> array
                                })
                    }
                }
            }.publish()

}