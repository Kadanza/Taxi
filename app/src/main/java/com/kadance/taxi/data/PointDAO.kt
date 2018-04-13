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
        return realm.where(RPoint::class.java).findAllAsync()
    }

    fun fingByLocation( lat :Double, lng :Double): RPoint? {
        return realm.where(RPoint::class.java)
                .equalTo("lat", lat)
                .equalTo("lng", lng)
                .findFirst()
    }


    /**
     * если есть точка с координатами, возвратить ее, иначе создать новую
     */
    fun create(name: String, lat :Double, lng :Double ): RPoint {

        val point = fingByLocation(lat, lng)

        if(point != null){

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