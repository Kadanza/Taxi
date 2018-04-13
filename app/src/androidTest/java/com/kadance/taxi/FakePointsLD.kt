package com.kadance.taxi

import com.kadance.taxi.common.live.PointsLD
import com.kadance.taxi.common.repo.IDataRepo
import com.kadance.taxi.data.RPoint

/**
 * Created by Kenza on 11.04.2018.
 */
class FakePointsLD (repo : IDataRepo): PointsLD(repo ) {

    init {
        val list = ArrayList<RPoint>()


        val point = RPoint()
        val point2 = RPoint()
        val point3 = RPoint()

        point.lat = 11.0
        point.lng = 11.0
        point.title = "Point1"


        point2.lat = 11.0
        point2.lng = 12.0
        point2.title = "Point2"


        point2.lat = 11.0
        point2.lng = 12.2
        point2.title = "Point3"

        list.add(point)
        list.add(point2)
        list.add(point3)



        value = list
    }

}