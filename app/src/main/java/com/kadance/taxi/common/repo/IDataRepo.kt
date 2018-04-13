package com.kadance.taxi.common.repo

import com.kadance.taxi.data.RPoint
import io.realm.RealmResults

/**
 * Created by Kenza on 11.04.2018.
 */
interface IDataRepo {

    fun getAllPoints(): RealmResults<RPoint>
    fun editPoint(point : RPoint, name: String, lat: Double, lng: Double): RPoint
    fun removePoint(point : RPoint)

    fun createPoint(name: String, lat: Double, lng: Double): RPoint
}
