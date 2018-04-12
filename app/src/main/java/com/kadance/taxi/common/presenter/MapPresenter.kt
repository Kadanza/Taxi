package com.kadance.taxi.common.presenter

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.kadance.taxi.common.live.PointsLD
import com.kadance.taxi.common.live.UserLocationLD
import javax.inject.Inject


open  class MapPresenter @Inject constructor(
        val pointsLD : PointsLD,
        val userLocationLD: UserLocationLD ) : ViewModel() {


    open fun clickFab(){
        //Toast.makeText(context, "Fab clicked!", Toast.LENGTH_SHORT).show()
        Log.d("qwe","click fab")
    }

}