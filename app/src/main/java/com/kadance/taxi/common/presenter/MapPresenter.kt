package com.kadance.taxi.common.presenter

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.kadance.taxi.common.live.PointsLD
import com.kadance.taxi.common.live.UserCoordinatLD
import javax.inject.Inject


open  class MapPresenter @Inject constructor(
        val pointsLD : PointsLD,
        val userCoordinatLD: UserCoordinatLD ) : ViewModel() {


    open fun clickFab(){
        //Toast.makeText(context, "Fab clicked!", Toast.LENGTH_SHORT).show()
        Log.d("qwe","click fab")
    }

}