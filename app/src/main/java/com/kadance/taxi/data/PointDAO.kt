package com.kadance.taxi.data

import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject

/**
 * Created by Kenza on 11.04.2018.
 */
class PointDAO @Inject constructor(val realm: Lazy<Realm>)  {


    /**
     * find all
     * create
     * remove
     * edit
     *
     */
    fun findAll(): RealmResults<RPoint> {
        return realm.value.where(RPoint::class.java).findAllAsync()
    }



}