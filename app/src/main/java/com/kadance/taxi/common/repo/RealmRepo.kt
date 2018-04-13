package com.kadance.taxi.common.repo

import com.kadance.taxi.data.PointDAO
import com.kadance.taxi.data.RPoint
import com.kadance.taxi.net.GoogleServicerApi
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject

/**
 * Created by Kenza on 11.04.2018.
 */
open class RealmRepo @Inject constructor(val realm: Realm, val pointDao: PointDAO) : IDataRepo {


    override fun editPoint(point: RPoint, name: String, lat: Double, lng: Double): RPoint {
        realm.beginTransaction()
        val point = pointDao.update(point, name, lat, lng)
        realm.commitTransaction()
        return point
    }


    override fun getAllPoints(): RealmResults<RPoint> {
        return pointDao.findAll()
    }

    override fun createPoint(name: String, lat: Double, lng: Double): RPoint {
        realm.beginTransaction()
        val point = pointDao.create(name, lat, lng)
        realm.commitTransaction()
        return point

    }

    override fun removePoint(point: RPoint) {
        realm.beginTransaction()
        pointDao.remove(point)
        realm.commitTransaction()
    }







}