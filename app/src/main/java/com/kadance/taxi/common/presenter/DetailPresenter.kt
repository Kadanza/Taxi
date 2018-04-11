package com.kadance.taxi.common.presenter

import android.arch.lifecycle.ViewModel
import android.util.Log
import javax.inject.Inject

/**
 * Created by Kenza on 11.04.2018.
 */


open  class DetailPresenter @Inject constructor() : ViewModel() {


    open fun clickFab(){
        //Toast.makeText(context, "Fab clicked!", Toast.LENGTH_SHORT).show()
        Log.d("qwe","click fab")
    }

}