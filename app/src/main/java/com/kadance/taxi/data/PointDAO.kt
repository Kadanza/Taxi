package com.kadance.taxi.data

import dagger.Lazy
import io.realm.Realm
import io.realm.RealmResults
import java.util.*
import javax.inject.Inject

/**
 * Created by Kenza on 11.04.2018.
 */
open class PointDAO @Inject constructor( var realm : Realm)  {



    /**
     * find all
     * create
     * remove
     * edit
     *
     */
    fun findAll(): RealmResults<RPoint> {
        return realm.where(RPoint::class.java).findAll()
    }

    fun fingByLocation( lat :Double, lng :Double): RPoint? {
        return realm.where(RPoint::class.java)
                .equalTo("lat", lat)
                .equalTo("lng", lng)
                .findFirst()
    }


    fun remove(rPoint: RPoint){
        rPoint.deleteFromRealm()
    }


    fun update(rPoint: RPoint, name: String, lat :Double, lng :Double ): RPoint {
        rPoint.title = name
        rPoint.lat = lat
        rPoint.lng = lng

        return rPoint
    }




    /**
     * если есть точка с координатами, возвратить ее, иначе создать новую
     */
    fun create(name: String, lat :Double, lng :Double ): RPoint {

        val point = fingByLocation(lat, lng)

        if(point != null){
            point.title = name
            return point

        }else{
            val newp = realm.createObject(RPoint::class.java, UUID.randomUUID().toString())
            newp.title = name
            newp.lat = lat
            newp.lng = lng

            return newp
        }
    }



}