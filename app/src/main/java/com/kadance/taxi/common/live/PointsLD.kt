package com.kadance.taxi.common.live

import android.arch.lifecycle.LiveData
import com.kadance.taxi.data.RPoint
import kotlinx.coroutines.experimental.Deferred
import javax.inject.Inject

/**
 * Created by Kenza on 11.04.2018.
 */
open class PointsLD  @Inject constructor()  :  LiveData< List<RPoint> >() {




    init {
        val list = ArrayList<RPoint>()


        val point = RPoint()
        val point2 = RPoint()

        point.lat = 34.0
        point.lng = -86.0
        point.title = "Point1"


        point2.lat = 2.0
        point2.lng = 2.0
        point2.title = "Point2"

        list.add(point)
        list.add(point2)


        value = list
    }


    var x = 0
    override fun onActive() {
        super.onActive()
    }

    override fun onInactive() {
        super.onInactive()
    }




}