package com.kadance.taxi.common.domain

import com.kadance.taxi.data.PointDAO
import com.kadance.taxi.data.RPoint
import io.realm.RealmResults
import javax.inject.Inject

/**
 * Created by Kenza on 11.04.2018.
 */
open class RealmRepo : IDataRepo {


    @Inject lateinit var  pointDao : PointDAO


    override fun getAllPoints(): RealmResults<RPoint> {
        return pointDao.findAll()
    }

    override fun createPoint() {
    }

    override fun editPoint() {
    }


}